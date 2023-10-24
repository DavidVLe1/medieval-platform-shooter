package learn.platformShooter.controllers;

import learn.platformShooter.domain.WorldStatsService;
import learn.platformShooter.models.WorldStats;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WorldStatsController {
    private final WorldStatsService worldStatsService;
    public WorldStatsController(WorldStatsService worldStatsService){this.worldStatsService=worldStatsService;}

    @GetMapping
    public List<WorldStats> findAll(){
        return worldStatsService.findAll ();
    }
    @GetMapping("/{worldStatsId}")
    public ResponseEntity<WorldStats> findById(@PathVariable int worldStatsId){
        WorldStats worldStats=worldStatsService.findById (worldStatsId);
        if(worldStats==null){
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok (worldStats);
    }
    @GetMapping("/{playerCharacterId}")
    public ResponseEntity<WorldStats> findByPlayerId(@PathVariable int playerCharacterId){
        WorldStats worldStats=worldStatsService.findByPlayerId (playerCharacterId);
        if(worldStats==null){
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok (worldStats);
    }
    //implement create
    //implement update
    //implement delete
}
