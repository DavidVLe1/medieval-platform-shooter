package learn.platformShooter.domain;

import learn.platformShooter.data.GameEventsRepository;
import learn.platformShooter.models.GameEvents;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameEventsService {
    private final GameEventsRepository repository;
    public GameEventsService(GameEventsRepository repository){this.repository=repository;}

    public List<GameEvents> findAll(){
        return repository.findAll ();
    }//not likely to be used as one instance of game events will be for one player character
    public GameEvents findById(int gameEventsId){
        return repository.findById (gameEventsId);
    }
    public GameEvents findByPlayerCharacterId(int playerCharacterId){
        return repository.findByPlayerCharacterId (playerCharacterId);
    }
    public Result<GameEvents> add(GameEvents gameEvents){
        Result<GameEvents> result = validate (gameEvents);
        if (!result.isSuccess()) {
            return result;
        }
        if (gameEvents.getGameEventsId () != 0) {
            result.addMessage("gameEventsId cannot be set for `add` operation.", ResultType.INVALID);
            return result;
        }
        if(gameEvents.isGameCompleted ()){
            result.addMessage("gameEvents GameCompleted cannot be true for `add` operation.", ResultType.INVALID);
            return result;
        }
        if(gameEvents.isLegendaryItemObtained ()){
            result.addMessage("gameEvents LegendaryItemObtained cannot be true for `add` operation.", ResultType.INVALID);
            return result;
        }
        if(gameEvents.getBossesKilled ()!=0){
            result.addMessage("gameEvents bossesKilled cannot be anything other than 0 for `add` operation.", ResultType.INVALID);
            return result;
        }
        gameEvents = repository.add(gameEvents);
        result.setPayload(gameEvents);
        return result;
    }
    public Result<GameEvents>update( GameEvents gameEvents){
        Result<GameEvents> result = validate (gameEvents);
        if (!result.isSuccess()) {
            return result;
        }
        if (gameEvents.getGameEventsId () <= 0) {
            result.addMessage("gameEventsId must be set for `update` operation.", ResultType.INVALID);
            return result;
        }
        if (!repository.update(gameEvents)) {
            String msg = String.format("gameEvents: %s, not found.", gameEvents.getGameEventsId ());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;

    }
    public Result<GameEvents> deleteById(int gameEventsId){
        Result<GameEvents> result = new Result<> ();
        if (!repository.deleteById(gameEventsId)) {
            String msg = String.format("GameEvents id: %s, not found", gameEventsId);
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    }

    private Result<GameEvents> validate(GameEvents gameEvents){
        Result<GameEvents> result = new Result<> ();
        if (gameEvents == null) {
            result.addMessage("gameEvents cannot be null", ResultType.INVALID);
            return result;
        }
        if (gameEvents.getPlayerCharacterId ()<=0) {
            result.addMessage("Player Character Id is required and must be more than 0.", ResultType.INVALID);
        }
        if (gameEvents.getBossesKilled ()<0) {
            result.addMessage("gameEvents bossesKilled can't be negative.", ResultType.INVALID);
        }
        return result;
    }

}
