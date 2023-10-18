package learn.platformShooter.data.mappers;

import learn.platformShooter.models.Leaderboard;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LeaderboardMapper implements RowMapper<Leaderboard> {
    @Override
    public Leaderboard mapRow(ResultSet resultSet, int i)throws SQLException {
        Leaderboard leaderboard = new Leaderboard ();
        leaderboard.setLeaderboardId (resultSet.getInt ("leaderboard_id"));
        leaderboard.setUserId (resultSet.getInt ("user_id"));
        leaderboard.setUsername (resultSet.getString ("username"));
        leaderboard.setScore (resultSet.getInt ("score"));
        return leaderboard;
    }
}
