package learn.platformShooter.controllers;

import learn.platformShooter.domain.GameEventsService;
import learn.platformShooter.models.GameEvents;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    //implement update
    //implement delete
}
