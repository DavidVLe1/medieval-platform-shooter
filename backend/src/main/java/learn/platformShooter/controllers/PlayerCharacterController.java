package learn.platformShooter.controllers;

import learn.platformShooter.domain.PlayerCharacterService;
import learn.platformShooter.domain.Result;
import learn.platformShooter.models.PlayerCharacter;
import learn.platformShooter.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playerCharacter")
public class PlayerCharacterController {
    private final PlayerCharacterService playerCharacterService;
    public PlayerCharacterController(PlayerCharacterService playerCharacterService){this.playerCharacterService=playerCharacterService;}

    @GetMapping
    public List<PlayerCharacter> findAll(){
        return playerCharacterService.findAll ();
    }
    @GetMapping("/{playerCharacterId}")
    public ResponseEntity<PlayerCharacter> findById(@PathVariable int playerCharacterId){
        PlayerCharacter playerCharacter = playerCharacterService.findByPlayerId (playerCharacterId);
        if(playerCharacter==null){
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok (playerCharacter);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<PlayerCharacter> findByUserId(@PathVariable int userId){
        PlayerCharacter playerCharacter = playerCharacterService.findByUserId (userId);
        if(playerCharacter==null){
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok (playerCharacter);
    }

    //implement create
    @PostMapping
    public ResponseEntity<Object> add(@RequestBody PlayerCharacter playerCharacter){
        Result<PlayerCharacter> result = playerCharacterService.add (playerCharacter);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }
    //implement update
    @PutMapping("/{playerCharacterId}")
    public ResponseEntity<Object> update(@PathVariable int playerCharacterId, @RequestBody PlayerCharacter playerCharacter){
        if(playerCharacterId!=playerCharacter.getPlayerCharacterId ()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Result<PlayerCharacter> result = playerCharacterService.update (playerCharacter);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }
    //implement delete
    @DeleteMapping("/{playerCharacterId}")
    public ResponseEntity<PlayerCharacter> deleteById(@PathVariable int playerCharacterId){
        Result<PlayerCharacter> result = playerCharacterService.deleteById (playerCharacterId);
        if(result.isSuccess ()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
