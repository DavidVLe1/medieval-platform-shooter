package learn.platformShooter.controllers;

import learn.platformShooter.domain.GameEventsService;
import learn.platformShooter.domain.Result;
import learn.platformShooter.models.GameEvents;
import learn.platformShooter.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gameEvents")
public class GameEventsController {
    private final GameEventsService gameEventsService;
    public GameEventsController(GameEventsService gameEventsService){this.gameEventsService=gameEventsService;}

    @GetMapping
    public List<GameEvents> findAll(){
        return gameEventsService.findAll ();
    }
    @GetMapping("/{playerCharacterId}")
    public ResponseEntity<GameEvents> findByPlayerCharacterId(@PathVariable int playerCharacterId){
        GameEvents gameEvents = gameEventsService.findByPlayerCharacterId (playerCharacterId);
        if(gameEvents==null){
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok (gameEvents);
    }

    @GetMapping("/{gameEventsId}")
    public ResponseEntity<GameEvents> findByGameEventsId(@PathVariable int gameEventsId){
        GameEvents gameEvents = gameEventsService.findById (gameEventsId);
        if(gameEvents==null){
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok (gameEvents);
    }
    //implement create
    @PostMapping
    public ResponseEntity<Object> add(@RequestBody GameEvents gameEvents){
        Result<GameEvents> result = gameEventsService.add (gameEvents);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }
    //implement update
    @PutMapping("/{gameEventsId}")
    public ResponseEntity<Object> update(@PathVariable int gameEventsId,@RequestBody GameEvents gameEvents) {
        if (gameEventsId != gameEvents.getGameEventsId ()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Result<GameEvents> result = gameEventsService.update (gameEvents);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }
    //implement delete
    @DeleteMapping("/{gameEventsId}")
    public ResponseEntity<User> deleteById(@PathVariable int gameEventsId) {
        Result<GameEvents> result =gameEventsService.deleteById (gameEventsId);
        if (result.isSuccess ()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
