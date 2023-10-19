package learn.platformShooter.domain;

import learn.platformShooter.data.PlayerCharacterRepository;
import learn.platformShooter.models.PlayerCharacter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlayerCharacterService {
    private final PlayerCharacterRepository repository;
    public PlayerCharacterService(PlayerCharacterRepository repository){this.repository=repository;}
    public List<PlayerCharacter> findAll(){
        return repository.findAll ();
    }

    public PlayerCharacter findByUserId(int userId){ //finds character through user
        return repository.findByUserId (userId);
    }

    public PlayerCharacter findByPlayerId(int playerCharacterId){ //finds specific character.
        return repository.findByPlayerId (playerCharacterId);
    }

    public Result<PlayerCharacter>  add(PlayerCharacter playerCharacter){
        Result<PlayerCharacter> result = validate(playerCharacter);
        if (!result.isSuccess()) {
            return result;
        }
        if (playerCharacter.getPlayerCharacterId () != 0) {
            result.addMessage("playerCharacterId cannot be set for `add` operation.", ResultType.INVALID);
            return result;
        }
        playerCharacter = repository.add(playerCharacter);
        result.setPayload(playerCharacter);
        return result;
    }

    public Result<PlayerCharacter>  update(PlayerCharacter playerCharacter){ //for updating stats.
        Result<PlayerCharacter> result = validate(playerCharacter);
        if (!result.isSuccess()) {
            return result;
        }

        if (playerCharacter.getPlayerCharacterId () <= 0) {
            result.addMessage("playerCharacterId must be set for `update` operation.", ResultType.INVALID);
            return result;
        }
        if (!repository.update(playerCharacter)) {
            String msg = String.format("playerCharacterId: %s, not found", playerCharacter.getPlayerCharacterId ());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    }

    public Result<PlayerCharacter>  deleteById(int playerCharacterId){ //delete a player
        Result<PlayerCharacter> result = new Result<> ();
        if (!repository.deleteById(playerCharacterId)) {
            String msg = String.format("playerCharacter id: %s, not found", playerCharacterId);
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    }

    public Result<PlayerCharacter> validate(PlayerCharacter playerCharacter){
        Result<PlayerCharacter> result = new Result<> ();
        if (playerCharacter == null) {
            result.addMessage("playerCharacter cannot be null", ResultType.INVALID);
            return result;
        }
        if(playerCharacter.getUserId ()<=0){
            result.addMessage("playerCharacter userId is required to be more than 0.", ResultType.INVALID);
        }
        if(playerCharacter.getTimePlayedInSeconds ()<0){
            result.addMessage("playerCharacter timePlayedInSeconds is required to be more than or equal to 0.", ResultType.INVALID);
        }
        if(playerCharacter.getCharactersLevel ()<=0){
            result.addMessage("playerCharacter level is required to be more than 0.", ResultType.INVALID);
        }
        if(playerCharacter.getMaxHealth ()<=0){
            result.addMessage("playerCharacter maxHealth is required to be more than 0.", ResultType.INVALID);
        }
        if(playerCharacter.getDamage ()<=0){
            result.addMessage("playerCharacter damage is required to be more than 0.", ResultType.INVALID);
        }
        if(playerCharacter.getSpeed ()<=0){
            result.addMessage("playerCharacter speed is required to be more than 0.", ResultType.INVALID);
        }
        if(playerCharacter.getHealingPotions ()<0){
            result.addMessage("playerCharacter healingPotions is required to be more than or equal to 0.", ResultType.INVALID);
        }
        return result;
    }
}
