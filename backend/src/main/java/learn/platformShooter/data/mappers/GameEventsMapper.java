package learn.platformShooter.data.mappers;

import learn.platformShooter.models.GameEvents;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameEventsMapper implements RowMapper<GameEvents> {
    @Override
    public GameEvents mapRow(ResultSet resultSet, int i)throws SQLException {
        GameEvents gameEvents = new GameEvents ();
        gameEvents.setGameEventsId (resultSet.getInt ("game_events_id"));
        gameEvents.setPlayerCharacterId (resultSet.getInt ("player_character_id"));
        gameEvents.setBossesKilled (resultSet.getInt ("bosses_killed"));
        gameEvents.setLegendaryItemObtained (resultSet.getBoolean ("legendary_item_obtained"));
        gameEvents.setGameCompleted (resultSet.getBoolean ("game_completed"));

        return gameEvents;
    }

}
