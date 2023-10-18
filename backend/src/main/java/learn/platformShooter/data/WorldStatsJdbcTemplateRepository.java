package learn.platformShooter.data;

import learn.platformShooter.data.mappers.WorldStatsMapper;
import learn.platformShooter.models.WorldStats;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class WorldStatsJdbcTemplateRepository implements WorldStatsRepository{
    JdbcTemplate jdbcTemplate;
    WorldStatsJdbcTemplateRepository(JdbcTemplate jdbcTemplate){this.jdbcTemplate=jdbcTemplate;}

    @Override
    public List<WorldStats> findAll() {
        final String sql = "select world_stats_id, player_character_id, enemies_killed, item_used, times_died"
                +"from world_stats;";
        return jdbcTemplate.query (sql,new WorldStatsMapper ());
    }

    @Override
    public WorldStats findById(int worldStatsId) {
        final String sql = "select world_stats_id, player_character_id, enemies_killed, item_used, times_died "
                +"from world_stats "
                +"where world_stats_id = ?;";
        return jdbcTemplate.query (sql,new WorldStatsMapper()).stream ().findFirst ().orElse (null);
    }

    @Override
    public WorldStats findByPlayerId(int playerCharacterId) {
        final String sql = "select world_stats_id, player_character_id, enemies_killed, item_used, times_died "
                +"from world_stats "
                +"where player_character_id = ?;";
        return jdbcTemplate.query (sql,new WorldStatsMapper()).stream ().findFirst ().orElse (null);
    }

    @Override
    public WorldStats add(WorldStats worldStats) {
        final String sql = "insert into world_stats (player_character_id, enemies_killed, item_used, times_died )"
                +"values (?,?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder ();
        int rowsAffected = jdbcTemplate.update (connection -> {
            PreparedStatement ps = connection.prepareStatement (sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt (1, worldStats.getPlayerCharacterId ());
            ps.setInt (2,worldStats.getEnemiesKilled ());
            ps.setInt (3,worldStats.getItemsUsed ());
            ps.setInt (4, worldStats.getTimesDied ());
            return ps;
        },keyHolder);
        if(rowsAffected<=0){
            return null;
        }
        worldStats.setWorldStatsId (keyHolder.getKey ().intValue ());
        return worldStats;
    }

    @Override
    public boolean update(WorldStats worldStats) {
        final String sql = "update world_stats set"
                +"player_character_id = ?, "
                +"enemies_killed = ?, "
                +"item_used = ?, "
                +"times_died = ? "
                +"where world_stats_id = ?;";
        return jdbcTemplate.update (sql,
                worldStats.getPlayerCharacterId (),
                worldStats.getEnemiesKilled (),
                worldStats.getItemsUsed (),
                worldStats.getTimesDied (),
                worldStats.getWorldStatsId ())>0;
    }

    @Override
    @Transactional
    public boolean deleteById(int worldStatsId) {
        return jdbcTemplate.update ("delete from world_Stats where world_stats_id = ?;", worldStatsId)>0;
    }
}
