package learn.platformShooter.data;

import learn.platformShooter.models.Enemy;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EnemyRepository {
    List<Enemy> findAll();

    Enemy findById(int enemyId);
    List<Enemy> findByType(String enemyType);

    Enemy add(Enemy enemy);

    boolean update(Enemy enemy);

    @Transactional
    boolean deleteById(int enemyId);
}
