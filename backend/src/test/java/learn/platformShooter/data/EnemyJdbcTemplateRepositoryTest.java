package learn.platformShooter.data;

import learn.platformShooter.models.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class EnemyJdbcTemplateRepositoryTest {
    @Autowired
    EnemyJdbcTemplateRepository repository;
    @Autowired
    KnownGoodState knownGoodState;
    @BeforeEach
    void setup(){knownGoodState.set ();}

    @Test
    void findAll() {
        List<Enemy> enemies = repository.findAll ();
        assertNotNull (enemies);
    }

    @Test
    void findById() {
        Enemy goblin = repository.findById (1);
        Enemy expected = new Enemy (1,"Goblin", "Small", 10, 50, 5);
        assertNotNull (goblin);
        assertEquals (expected,goblin);
    }

    @Test
    void findByType() {
        List<Enemy> smallEnemies= repository.findByType ("Small");
        assertNotNull (smallEnemies);
        assertEquals (2,smallEnemies.size ());
    }

    @Test
    void add() {
        Enemy chicken = new Enemy (0, "Chicken", "Small", 5,10,3);
        Enemy actual = repository.add (chicken);
        assertNotNull (actual);
        assertEquals (4,actual.getEnemyId ());
    }

    @Test
    void update() {
        Enemy expected = new Enemy (3,"High Elf", "Small", 30, 150, 7);
        Enemy elf=repository.findById (3);
        elf.setEnemyName ("High Elf");
        elf.setDamage (30);
        elf.setHealth (150);
        elf.setSpeed (7);
        assertTrue (repository.update (elf));
        assertEquals (expected,elf);
    }

    @Test
    void deleteById() {
        assertTrue (repository.deleteById (2));
    }
}