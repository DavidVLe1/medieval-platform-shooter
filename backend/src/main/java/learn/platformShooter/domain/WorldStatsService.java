package learn.platformShooter.domain;

import learn.platformShooter.data.WorldStatsRepository;
import learn.platformShooter.models.WorldStats;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorldStatsService {
    private final WorldStatsRepository repository;
    public WorldStatsService(WorldStatsRepository repository){this.repository=repository;}

    public List<WorldStats> findAll(){
        return repository.findAll ();
    }
    public WorldStats findById(int worldStatsId){
        return repository.findById (worldStatsId);
    }
    public WorldStats findByPlayerId(int playerCharacterId){
        return repository.findByPlayerId (playerCharacterId);
    }
    public Result<WorldStats> add(WorldStats worldStats){
        Result<WorldStats> result = validate (worldStats);

        if (!result.isSuccess()) {
            return result;
        }

        if (worldStats.getWorldStatsId () != 0) {
            result.addMessage("worldStatsId cannot be set for `add` operation.", ResultType.INVALID);
            return result;
        }
        if (worldStats.getPlayerCharacterId ()==0) {
            result.addMessage("worldStats playerCharacterId must be set for `add` operation.", ResultType.INVALID);
            return result;
        }
        if (worldStats.getEnemiesKilled ()!=0) {
            result.addMessage("worldStats enemiesKilled cannot be set for `add` operation.", ResultType.INVALID);
            return result;
        }
        if (worldStats.getItemsUsed ()!=0) {
            result.addMessage("worldStats itemUsed cannot be set for `add` operation.", ResultType.INVALID);
            return result;
        }
        if (worldStats.getTimesDied ()!=0) {
            result.addMessage("worldStats times_died cannot be set for `add` operation.", ResultType.INVALID);
            return result;
        }
        worldStats = repository.add(worldStats);
        result.setPayload(worldStats);
        return result;
    }
    public Result<WorldStats> update(WorldStats worldStats){
        Result<WorldStats> result = validate (worldStats);
        if (!result.isSuccess()) {
            return result;
        }

        if (worldStats.getPlayerCharacterId ()<0) {
            result.addMessage("worldStats playerCharacterId can't be negative.", ResultType.INVALID);
            return result;
        }

        if (worldStats.getWorldStatsId () <= 0) {
            result.addMessage("worldStatsId must be set for `update` operation.", ResultType.INVALID);
            return result;
        }
        if (!repository.update(worldStats)) {
            String msg = String.format("worldStatsId: %s, not found.", worldStats.getWorldStatsId ());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    }
    public Result<WorldStats> deleteById(int worldStatsId){
        Result<WorldStats> result = new Result<> ();
        if (!repository.deleteById(worldStatsId)) {
            String msg = String.format("worldStats id: %s, not found.", worldStatsId);
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    }

    public Result<WorldStats> validate(WorldStats worldStats){
        Result<WorldStats> result = new Result<> ();
        if (worldStats == null) {
            result.addMessage("worldStats cannot be null", ResultType.INVALID);
            return result;
        }

        if (worldStats.getEnemiesKilled ()<0) {
            result.addMessage("worldStats enemiesKilled is required to be 0 or more.", ResultType.INVALID);
        }
        if (worldStats.getItemsUsed ()<0) {
            result.addMessage("worldStats itemUsed is required to be 0 or more.", ResultType.INVALID);
        }
        if (worldStats.getTimesDied ()<0) {
            result.addMessage("worldStats times_died is required to be 0 or more.", ResultType.INVALID);
        }
        return result;
    }

}
