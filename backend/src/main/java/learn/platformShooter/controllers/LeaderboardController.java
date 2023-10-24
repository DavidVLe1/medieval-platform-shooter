package learn.platformShooter.controllers;

import learn.platformShooter.domain.LeaderboardService;
import learn.platformShooter.domain.Result;
import learn.platformShooter.models.Leaderboard;
import learn.platformShooter.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {
    private final LeaderboardService leaderboardService;
    public LeaderboardController(LeaderboardService leaderboardService){this.leaderboardService=leaderboardService;}

    @GetMapping
    public List<Leaderboard> findAll(){
        return leaderboardService.findAll ();
    }

    @GetMapping("/{leaderboardId}")
    public ResponseEntity<Leaderboard> findById(@PathVariable int leaderboardId){
        Leaderboard leaderboard=leaderboardService.findById (leaderboardId);
        if(leaderboard==null){
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok (leaderboard);
    }
    //implement create
    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Leaderboard leaderboard){
        Result<Leaderboard> result = leaderboardService.add (leaderboard);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }
    //implement update
    @PutMapping("/{leaderboardId}")
    public ResponseEntity<Object> update(@PathVariable int leaderboardId,@RequestBody Leaderboard leaderboard) {
        if (leaderboardId != leaderboard.getUserId ()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Result<Leaderboard> result = leaderboardService.update (leaderboard);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }
    //implement delete
    @DeleteMapping("/{leaderboardId}")
    public ResponseEntity<User> deleteById(@PathVariable int leaderboardId) {
        Result<Leaderboard> result =leaderboardService.deleteById (leaderboardId);
        if (result.isSuccess ()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
