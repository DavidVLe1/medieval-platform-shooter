package learn.platformShooter.data.mappers;

import learn.platformShooter.models.PlayerCharacter;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerCharacterMapper implements RowMapper<PlayerCharacter> {
    @Override
    public PlayerCharacter mapRow(ResultSet resultSet, int i)throws SQLException {
        PlayerCharacter playerCharacter = new PlayerCharacter ();
        playerCharacter.setPlayerCharacterId (resultSet.getInt ("player_character_id"));
        playerCharacter.setUserId (resultSet.getInt ("user_id"));
        playerCharacter.setTimePlayedInSeconds (resultSet.getInt ("time_played_in_seconds"));
        playerCharacter.setCharactersLevel (resultSet.getDouble ("characters_level"));
        playerCharacter.setMaxHealth (resultSet.getDouble ("max_health"));
        playerCharacter.setHealth (resultSet.getDouble ("health"));
        playerCharacter.setDamage (resultSet.getDouble ("damage"));
        playerCharacter.setSpeed (resultSet.getDouble ("speed"));
        playerCharacter.setHealingPotions (resultSet.getInt ("healing_potions"));

        return playerCharacter;
    }


}
