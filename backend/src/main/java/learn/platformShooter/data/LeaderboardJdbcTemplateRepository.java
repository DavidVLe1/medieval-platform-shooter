package learn.platformShooter.data;

import learn.platformShooter.data.mappers.LeaderboardMapper;
import learn.platformShooter.models.Leaderboard;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class LeaderboardJdbcTemplateRepository implements LeaderboardRepository{
    JdbcTemplate jdbcTemplate;
    LeaderboardJdbcTemplateRepository(JdbcTemplate jdbcTemplate){this.jdbcTemplate=jdbcTemplate;}

    @Override
    public List<Leaderboard> findAll() {
        final String sql = "select leaderboard_id, user_id, username, score"
                +"from leaderboard;";
        return jdbcTemplate.query (sql,new LeaderboardMapper());
    }

    @Override
    public Leaderboard findById(int leaderboard_id) {
        final String sql = "select leaderboard_id, user_id, username, score "
                +"from leaderboard "
                +"where leaderboard_id = ?;";
        return jdbcTemplate.query (sql,new LeaderboardMapper(), leaderboard_id).stream ().findFirst ().orElse (null);
    }

    @Override
    public Leaderboard add(Leaderboard leaderboard) {
        final String sql = "insert into leaderboard (user_id, username, score )"
                +"values (?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder ();
        int rowsAffected = jdbcTemplate.update (connection ->{
            PreparedStatement ps = connection.prepareStatement (sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt (1,leaderboard.getUserId ());
            ps.setString (2,leaderboard.getUsername ());
            ps.setInt (3,leaderboard.getScore ());
            return ps;
        },keyHolder);
        if(rowsAffected<=0){
            return null;
        }
        leaderboard.setLeaderboardId (keyHolder.getKey().intValue ());
        return leaderboard;
    }

    @Override
    public boolean update(Leaderboard leaderboard) {
        final String sql = "update leaderboard set "
                +"user_id = ?, "
                +"username = ?, "
                +"score = ? "
                +"where leaderboard_id = ?;";
        return jdbcTemplate.update(sql,
                leaderboard.getUserId (),
                leaderboard.getUsername (),
                leaderboard.getScore (),
                leaderboard.getLeaderboardId ())>0;
    }

    @Override
    public boolean deleteById(int leaderboard_id) {
        return jdbcTemplate.update ("delete from leaderboard where leaderboard_id = ?", leaderboard_id)>0;
    }
}
