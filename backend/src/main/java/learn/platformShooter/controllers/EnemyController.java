package learn.platformShooter.controllers;

import learn.platformShooter.domain.EnemyService;
import learn.platformShooter.models.Enemy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    //implement update
    //implement delete
}
