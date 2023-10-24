package learn.platformShooter.controllers;

import learn.platformShooter.domain.LeaderboardService;
import learn.platformShooter.models.Leaderboard;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{LeaderboardId}")
    public ResponseEntity<Leaderboard> findById(@PathVariable int leaderboardId){
        Leaderboard leaderboard=leaderboardService.findById (leaderboardId);
        if(leaderboard==null){
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok (leaderboard);
    }
    //implement create
    //implement update
    //implement delete

}
