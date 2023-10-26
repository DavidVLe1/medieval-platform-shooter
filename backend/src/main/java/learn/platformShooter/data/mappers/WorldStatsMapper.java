package learn.platformShooter.data.mappers;

import learn.platformShooter.models.WorldStats;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorldStatsMapper implements RowMapper<WorldStats> {
    @Override
        public WorldStats mapRow(ResultSet resultSet, int i)throws SQLException {
        WorldStats worldStats = new WorldStats ();
        worldStats.setWorldStatsId (resultSet.getInt ("world_stats_id"));
        worldStats.setPlayerCharacterId (resultSet.getInt ("player_character_id"));
        worldStats.setEnemiesKilled (resultSet.getInt ("enemies_killed"));
        worldStats.setItemsUsed (resultSet.getInt ("item_used"));
        worldStats.setTimesDied (resultSet.getInt ("times_died"));
        return worldStats;
    }
}
