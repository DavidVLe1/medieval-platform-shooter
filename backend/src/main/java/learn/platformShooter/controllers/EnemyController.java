package learn.platformShooter.controllers;

import learn.platformShooter.domain.EnemyService;
import learn.platformShooter.domain.Result;
import learn.platformShooter.models.Enemy;
import learn.platformShooter.models.GameEvents;
import learn.platformShooter.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enemy")
public class EnemyController {
    private final EnemyService enemyService;
    public EnemyController(EnemyService enemyService){this.enemyService=enemyService;}

    @GetMapping
    public List<Enemy> findAll(){return enemyService.findAll ();}

    @GetMapping("/{enemyType}")
    public List<Enemy> findByType(@PathVariable String enemyType){
        return enemyService.findByType (enemyType);
    }

    @GetMapping("/{enemyId}")
    public ResponseEntity<Enemy> findById(@PathVariable int enemyId){
        Enemy enemy= enemyService.findById (enemyId);
        if(enemy ==null){
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(enemy);
    }

    //implement create
    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Enemy enemy){
        Result<Enemy> result = enemyService.add (enemy);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }
    //implement update
    @PutMapping("/{enemyId}")
    public ResponseEntity<Object> update(@PathVariable int enemyId,@RequestBody Enemy enemy) {
        if (enemyId != enemy.getEnemyId ()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Result<Enemy> result = enemyService.update (enemy);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }
    //implement delete
    @DeleteMapping("/{enemyId}")
    public ResponseEntity<User> deleteById(@PathVariable int enemyId) {
        Result<Enemy> result =enemyService.deleteById (enemyId);
        if (result.isSuccess ()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
