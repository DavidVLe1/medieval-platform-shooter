package learn.platformShooter.data.mappers;

import learn.platformShooter.models.Enemy;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EnemyMapper implements RowMapper<Enemy> {
    @Override
    public Enemy mapRow(ResultSet resultSet, int i)throws SQLException {
        Enemy enemy = new Enemy ();
        enemy.setEnemyId (resultSet.getInt ("enemy_id"));
        enemy.setEnemyName (resultSet.getString ("enemy_name"));
        enemy.setEnemyType (resultSet.getString ("enemy_type"));
        enemy.setDamage (resultSet.getDouble ("damage"));
        enemy.setHealth (resultSet.getDouble ("health"));
        enemy.setSpeed (resultSet.getDouble ("speed"));
        return enemy;
    }
}
