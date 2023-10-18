package learn.platformShooter.data;

import learn.platformShooter.models.PlayerCharacter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlayerCharacterRepository {
    List<PlayerCharacter> findAll();
    PlayerCharacter findByUserId(int userId);//finds character through user
    PlayerCharacter findByPlayerId(int playerCharacterId);//finds specific character.
    PlayerCharacter add(PlayerCharacter playerCharacter);
    boolean update(PlayerCharacter playerCharacter);//for updating stats.
    @Transactional
    boolean deleteById(int playerCharacterId);//delete a player

}
