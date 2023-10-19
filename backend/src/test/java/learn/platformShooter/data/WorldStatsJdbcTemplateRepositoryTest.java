package learn.platformShooter.data;

import learn.platformShooter.models.WorldStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class WorldStatsJdbcTemplateRepositoryTest {
    @Autowired
    WorldStatsJdbcTemplateRepository repository;
    @Autowired
    KnownGoodState knownGoodState;
    @BeforeEach
    void setup(){knownGoodState.set();}

    @Test
    void findAll() {
        List<WorldStats> worldStatsList = repository.findAll ();
        assertNotNull (worldStatsList);
    }

    @Test
    void findById() {
        WorldStats world1=repository.findById (1);
        assertNotNull (world1);
        WorldStats expected = new WorldStats (1,1,50,10,5);
        assertEquals (expected,world1);
    }

    @Test
    void findByPlayerId() {
        WorldStats player1World = repository.findByPlayerId (1);
        assertNotNull (player1World);
        WorldStats expected = new WorldStats (1,1,50,10,5);
        assertEquals (expected,player1World);
    }

    @Test
    void add() {
        WorldStats actual = new WorldStats (0,3,0,0,5);
        assertNotNull (repository.add (actual));
        assertEquals (3,actual.getWorldStatsId ());
        assertEquals (0,actual.getEnemiesKilled ());
        assertEquals (0,actual.getItemsUsed ());
        assertEquals (5,actual.getTimesDied ());
    }

    @Test
    void update() {
        WorldStats updatedWorldStats = new WorldStats (2,2,0,0,0);
        assertTrue (repository.update (updatedWorldStats));
    }

    @Test
    void deleteById() {
        assertTrue (repository.deleteById (3));
    }
}