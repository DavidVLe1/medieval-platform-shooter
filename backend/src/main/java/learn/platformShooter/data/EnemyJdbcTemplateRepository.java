package learn.platformShooter.data;

import learn.platformShooter.data.mappers.EnemyMapper;
import learn.platformShooter.models.Enemy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class EnemyJdbcTemplateRepository implements EnemyRepository{

    private final JdbcTemplate jdbcTemplate;

    public EnemyJdbcTemplateRepository(JdbcTemplate jdbcTemplate){this.jdbcTemplate=jdbcTemplate;}

    @Override
    public List<Enemy> findAll() {
        final String sql = "select enemy_id, enemy_name, enemy_type, damage, health, speed "
                + "from enemy;";
        return jdbcTemplate.query (sql,new EnemyMapper ());
    }

    @Override
    public Enemy findById(int enemyId) {
        final String sql = "select enemy_id, enemy_name, enemy_type, damage, health, speed "
                + "from enemy "
                + "where enemy_id = ?;";
        return jdbcTemplate.query (sql, new EnemyMapper(), enemyId).stream().findFirst ().orElse (null);
    }

    @Override
    public List<Enemy> findByType(String enemyType) {
        final String sql = "select enemy_id, enemy_name, enemy_type, damage, health, speed "
                + "from enemy "
                + "where enemy_type = ?;";
        return jdbcTemplate.query (sql, new EnemyMapper(), enemyType);
    }

    @Override
    public Enemy add(Enemy enemy) {
        final String sql = "insert into enemy (enemy_name, enemy_type, damage, health, speed) "
                +"values (?,?,?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder ();
        int rowsAffected = jdbcTemplate.update (connection -> {
            PreparedStatement ps = connection.prepareStatement (sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString (1,enemy.getEnemyName ());
            ps.setString (2, enemy.getEnemyType ());
            ps.setDouble (3,enemy.getDamage ());
            ps.setDouble (4,enemy.getHealth ());
            ps.setDouble (5,enemy.getSpeed ());
            return ps;
        },keyHolder);
        if(rowsAffected<=0){
            return null;
        }
        enemy.setEnemyId (keyHolder.getKey ().intValue ());
        return enemy;
    }

    @Override
    public boolean update(Enemy enemy) {
        final String sql = "update enemy set "
                +"enemy_name = ?, "
                +"enemy_type = ?, "
                +"damage = ?, "
                +"health = ?, "
                +"speed = ? "
                +"where enemy_id= ? ;";

        return jdbcTemplate.update (sql,
                enemy.getEnemyName (),
                enemy.getEnemyType (),
                enemy.getDamage (),
                enemy.getHealth (),
                enemy.getSpeed (),
                enemy.getEnemyId ())>0;
    }

    @Override
    @Transactional
    public boolean deleteById(int enemyId) {
        return jdbcTemplate.update ("delete from enemy where enemy_id = ?;",enemyId)>0;
    }
}
