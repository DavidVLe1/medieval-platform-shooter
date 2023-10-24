package learn.platformShooter.controllers;

import learn.platformShooter.domain.PlayerCharacterService;
import learn.platformShooter.models.PlayerCharacter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    //implement update
    //implement delete

}
