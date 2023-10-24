package learn.platformShooter.controllers;

import learn.platformShooter.domain.Result;
import learn.platformShooter.domain.WorldStatsService;
import learn.platformShooter.models.WorldStats;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public ResponseEntity<Object> add(@RequestBody WorldStats worldStats){
        Result<WorldStats> result = worldStatsService.add (worldStats);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }
    //implement update
    @PutMapping("/{worldStatsId}")
    public ResponseEntity<Object> update (@PathVariable int worldStatsId, @RequestBody WorldStats worldStats){
        if(worldStatsId!=worldStats.getWorldStatsId ()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Result<WorldStats> result = worldStatsService.update (worldStats);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }
    //implement delete
    @DeleteMapping("/{worldStatsId}")
    public ResponseEntity<Object> deleteById(@PathVariable int worldStatsId){
        Result<WorldStats> result = worldStatsService.deleteById (worldStatsId);
        if (result.isSuccess ()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
