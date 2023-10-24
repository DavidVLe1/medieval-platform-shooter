package learn.platformShooter.domain;

import learn.platformShooter.data.LeaderboardRepository;
import learn.platformShooter.models.Leaderboard;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderboardService {
    private final LeaderboardRepository repository;
    public LeaderboardService(LeaderboardRepository repository){this.repository=repository;}

    public List<Leaderboard> findAll(){
        return repository.findAll ();
    }

    public Leaderboard findById(int leaderboardId){
        return repository.findById (leaderboardId);
    }

    public Result<Leaderboard> add(Leaderboard leaderboard){
        Result<Leaderboard> result = validate (leaderboard);
        if (!result.isSuccess()) {
            return result;
        }

        if (leaderboard.getLeaderboardId () != 0) {
            result.addMessage("leaderboardId cannot be set for `add` operation.", ResultType.INVALID);
            return result;
        }
        leaderboard = repository.add(leaderboard);
        result.setPayload(leaderboard);
        return result;
    }

    public Result<Leaderboard> update(Leaderboard leaderboard){
        Result<Leaderboard> result = validate (leaderboard);
        if (!result.isSuccess()) {
            return result;
        }

        if (leaderboard.getLeaderboardId () <= 0) {
            result.addMessage("leaderboardId must be set for `update` operation.", ResultType.INVALID);
            return result;
        }
        if (!repository.update(leaderboard)) {
            String msg = String.format("leaderboardId: %s, not found.", leaderboard.getLeaderboardId ());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    }

    public Result<Leaderboard> deleteById(int leaderboardId){
        Result<Leaderboard> result = new Result<> ();
        if (!repository.deleteById(leaderboardId)) {
            String msg = String.format("leaderboard id: %s, not found", leaderboardId);
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    }

    public Result<Leaderboard> validate(Leaderboard leaderboard){
        Result<Leaderboard> result = new Result<> ();
        if (leaderboard == null) {
            result.addMessage("Leaderboard cannot be null", ResultType.INVALID);
            return result;
        }
        if (Validations.isNullOrBlank(leaderboard.getUsername ())) {
            result.addMessage("Leaderboard username is required.", ResultType.INVALID);
        }
        if (leaderboard.getUserId ()<=0) {
            result.addMessage("Leaderboard userId cannot be 0 and under.", ResultType.INVALID);
        }
        if (leaderboard.getScore ()<0) {
            result.addMessage("Leaderboard score cannot be less than 0.", ResultType.INVALID);
        }
        return result;
    }
}
