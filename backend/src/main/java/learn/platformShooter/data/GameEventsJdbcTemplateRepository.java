package learn.platformShooter.data;

import learn.platformShooter.data.mappers.GameEventsMapper;
import learn.platformShooter.models.GameEvents;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class GameEventsJdbcTemplateRepository implements GameEventsRepository{
    JdbcTemplate jdbcTemplate;
    GameEventsJdbcTemplateRepository(JdbcTemplate jdbcTemplate){this.jdbcTemplate=jdbcTemplate;}

    @Override
    public List<GameEvents> findAll() { //not likely to be used as one instance of game events will be for one player character
        final String sql = "select games_event_id, player_character_id, bosses_killed, legendary_item_obtained, game_completed "
                +"from game_events;";
        return jdbcTemplate.query (sql, new GameEventsMapper ());
    }

    @Override
    public GameEvents findById(int gameEventsId) {
        final String sql = "select games_event_id, player_character_id, bosses_killed, legendary_item_obtained, game_completed "
                +"from game_events"
                +"where games_event_id = ?;";
        return jdbcTemplate.query (sql, new GameEventsMapper (),gameEventsId).stream ().findFirst ().orElse (null);
    }

    @Override
    public GameEvents add(GameEvents gameEvents) {
        final String sql = "insert into gameEvents (player_character_id, bosses_killed, legendary_item_obtained, game_completed)"
                +"values (?,?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder ();
        int rowsAffected = jdbcTemplate.update (connection ->{
            PreparedStatement ps = connection.prepareStatement (sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt (1, gameEvents.getPlayerCharacterId ());
            ps.setInt (2, gameEvents.getBossesKilled ());
            ps.setBoolean (3, gameEvents.isLegendaryItemObtained ());
            ps.setBoolean (4,gameEvents.isGameCompleted ());
            return ps;
        },keyHolder);

        if(rowsAffected<=0){
            return null;
        }
        gameEvents.setGameEventsId (keyHolder.getKey().intValue());
        return gameEvents;
    }

    @Override
    public boolean update(GameEvents gameEvents) {
        final String sql = "update game_events set"
                +"player_character_id = ?, "
                +"bosses_killed = ?, "
                +"legendary_item_obtained = ?, "
                +"game_completed = ? "
                +"where game_events_id = ?;";
        return jdbcTemplate.update (sql,
                gameEvents.getPlayerCharacterId (),
                gameEvents.getBossesKilled (),
                gameEvents.isLegendaryItemObtained (),
                gameEvents.isGameCompleted (),
                gameEvents.getGameEventsId ())>0;
    }

    @Override
    public boolean deleteById(int gameEventsId) {
        return jdbcTemplate.update ("delete from game_events where game_events_id = ?;", gameEventsId)>0;
    }
}
