package learn.platformShooter.domain;

import learn.platformShooter.data.WorldStatsRepository;
import learn.platformShooter.models.WorldStats;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class WorldStatsServiceTest {
    @Autowired
    WorldStatsService service;
    @MockBean
    WorldStatsRepository repository;

    @Test
    void shouldFindAll() {
        WorldStats worldStats1 = new WorldStats (1,1,50,10,5);
        WorldStats worldStats2 = new WorldStats (2,2,100,25,8);
        List<WorldStats> worldStatsList=List.of (worldStats1,worldStats2);
        when(repository.findAll ()).thenReturn (worldStatsList);
        assertEquals (worldStatsList,service.findAll ());
    }

    @Test
    void shouldFindByWorldStatsId() {
        WorldStats worldStats = makeWorldStats ();
        when (repository.findById (1)).thenReturn (worldStats);
        WorldStats actual = service.findById (1);
        assertEquals (worldStats,actual);
        assertNull (service.findById (20));
    }

    @Test
    void shouldFindByPlayerId() {
        WorldStats worldStats = makeWorldStats ();
        when (repository.findByPlayerId (1)).thenReturn (worldStats);
        WorldStats actual = service.findByPlayerId (1);
        assertEquals (worldStats,actual);
        assertNull (service.findByPlayerId (20));
    }
    @Test
    void shouldAdd() {
    }
    @Test
    void shouldNotAdd() {
    }

    @Test
    void shouldUpdate() {
    }
    @Test
    void shouldNotUpdate() {
    }

    @Test
    void shouldDeleteById() {
    }
    @Test
    void shouldNotDeleteById() {
    }
    WorldStats makeWorldStats(){
        WorldStats worldStats = new WorldStats ();
        worldStats.setPlayerCharacterId (1);
        worldStats.setEnemiesKilled (50);
        worldStats.setItemsUsed (10);
        worldStats.setTimesDied (5);
        return worldStats;
    }
}