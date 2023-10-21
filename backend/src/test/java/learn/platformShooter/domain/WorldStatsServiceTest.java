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
        WorldStats worldStats = makeWorldStats ();
        worldStats.setWorldStatsId (0);
        worldStats.setTimesDied (0);
        worldStats.setItemsUsed (0);
        worldStats.setEnemiesKilled (0);
        worldStats.setPlayerCharacterId (3);
        WorldStats expected = makeWorldStats ();
        expected.setWorldStatsId (0);
        expected.setTimesDied (0);
        expected.setItemsUsed (0);
        worldStats.setEnemiesKilled (0);
        expected.setPlayerCharacterId (3);
        expected.setWorldStatsId (3);
        when (repository.add (worldStats)).thenReturn (expected);
        Result<WorldStats> result=service.add (worldStats);
        assertEquals (ResultType.SUCCESS,result.getType ());
        assertEquals (expected,result.getPayload ());
    }
    @Test
    void shouldNotAdd() {
        //can't add null
        Result<WorldStats> result = service.add (null);
        assertEquals (ResultType.INVALID, result.getType ());
        //should not add when worldstatsId is not 0
        WorldStats worldStats = makeWorldStats ();
        worldStats.setEnemiesKilled (0);
        worldStats.setItemsUsed (0);
        worldStats.setTimesDied (0);
        result=service.add (worldStats);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("worldStatsId cannot be set for `add` operation.", result.getMessages().get(0));
        //should not add when playerCharacterId is not set
        worldStats = makeWorldStats ();
        worldStats.setWorldStatsId (0);
        worldStats.setEnemiesKilled (0);
        worldStats.setPlayerCharacterId (0);
        worldStats.setItemsUsed (0);
        worldStats.setTimesDied (0);
        result=service.add (worldStats);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("worldStats playerCharacterId must be set for `add` operation.", result.getMessages().get(0));
        //should not add when enemies killed is set.
        worldStats = makeWorldStats ();
        worldStats.setEnemiesKilled (50);
        worldStats.setPlayerCharacterId (3);
        worldStats.setWorldStatsId (0);
        worldStats.setItemsUsed (0);
        worldStats.setTimesDied (0);
        result=service.add (worldStats);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("worldStats enemiesKilled cannot be set for `add` operation.", result.getMessages().get(0));
        //should not add when itemUsed is set.
        worldStats = makeWorldStats ();
        worldStats.setWorldStatsId (0);
        worldStats.setEnemiesKilled (0);
        worldStats.setPlayerCharacterId (3);
        worldStats.setItemsUsed (50);
        worldStats.setTimesDied (0);
        result=service.add (worldStats);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("worldStats itemUsed cannot be set for `add` operation.", result.getMessages().get(0));
        //should not add when timesDied is set.
        worldStats = makeWorldStats ();
        worldStats.setWorldStatsId (0);
        worldStats.setEnemiesKilled (0);
        worldStats.setPlayerCharacterId (3);
        worldStats.setItemsUsed (0);
        worldStats.setTimesDied (50);
        result=service.add (worldStats);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("worldStats times_died cannot be set for `add` operation.", result.getMessages().get(0));

        //should not add when enemies killed is negative.
        worldStats = makeWorldStats ();
        worldStats.setWorldStatsId (0);
        worldStats.setEnemiesKilled (-10);
        worldStats.setPlayerCharacterId (3);
        worldStats.setItemsUsed (0);
        worldStats.setTimesDied (0);
        result=service.add (worldStats);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("worldStats enemiesKilled is required to be 0 or more.", result.getMessages().get(0));
        //should not add when itemsUsed is negative.
        worldStats = makeWorldStats ();
        worldStats.setEnemiesKilled (0);
        worldStats.setWorldStatsId (0);
        worldStats.setPlayerCharacterId (3);
        worldStats.setItemsUsed (-10);
        worldStats.setTimesDied (0);
        result=service.add (worldStats);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("worldStats itemUsed is required to be 0 or more.", result.getMessages().get(0));
        //should not add when times_died is negative.
        worldStats = makeWorldStats ();
        worldStats.setEnemiesKilled (0);
        worldStats.setWorldStatsId (0);
        worldStats.setPlayerCharacterId (3);
        worldStats.setItemsUsed (0);
        worldStats.setTimesDied (-10);
        result=service.add (worldStats);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("worldStats times_died is required to be 0 or more.", result.getMessages().get(0));
    }

    @Test
    void shouldUpdate() {
        WorldStats worldStats = makeWorldStats ();
        when (repository.update (worldStats)).thenReturn (true);
        Result<WorldStats> actual = service.update (worldStats);
        assertEquals (ResultType.SUCCESS,actual.getType ());
    }
    @Test
    void shouldNotUpdate() {
        //can't update null
        Result<WorldStats> result = service.update (null);
        assertEquals (ResultType.INVALID, result.getType ());
        //worldstats playerCharacterId can't be negative.
        WorldStats worldStats = makeWorldStats ();
        worldStats.setPlayerCharacterId (-1);
        result=service.update (worldStats);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("worldStats playerCharacterId can't be negative.", result.getMessages().get(0));
        //worldStatsId must be set, not 0.
        worldStats = makeWorldStats ();
        worldStats.setWorldStatsId (-1);
        result=service.update (worldStats);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("worldStatsId must be set for `update` operation.", result.getMessages().get(0));
        //worldstats ID can't be missing.
        worldStats = makeWorldStats ();
        worldStats.setWorldStatsId (20);
        when(repository.update (worldStats)).thenReturn (false);
        result=service.update (worldStats);
        assertEquals(ResultType.NOT_FOUND, result.getType());
        assertEquals(String.format("worldStatsId: %s, not found.",20), result.getMessages().get(0));


        //should not add when enemies killed is negative.
        worldStats = makeWorldStats ();
        worldStats.setWorldStatsId (0);
        worldStats.setEnemiesKilled (-10);
        worldStats.setPlayerCharacterId (3);
        worldStats.setItemsUsed (0);
        worldStats.setTimesDied (0);
        result=service.update (worldStats);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("worldStats enemiesKilled is required to be 0 or more.", result.getMessages().get(0));
        //should not add when itemsUsed is negative.
        worldStats = makeWorldStats ();
        worldStats.setEnemiesKilled (0);
        worldStats.setWorldStatsId (0);
        worldStats.setPlayerCharacterId (3);
        worldStats.setItemsUsed (-10);
        worldStats.setTimesDied (0);
        result=service.update (worldStats);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("worldStats itemUsed is required to be 0 or more.", result.getMessages().get(0));
        //should not add when times_died is negative.
        worldStats = makeWorldStats ();
        worldStats.setEnemiesKilled (0);
        worldStats.setWorldStatsId (0);
        worldStats.setPlayerCharacterId (3);
        worldStats.setItemsUsed (0);
        worldStats.setTimesDied (-10);
        result=service.update (worldStats);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("worldStats times_died is required to be 0 or more.", result.getMessages().get(0));

    }

    @Test
    void shouldDeleteById() {
        when(repository.deleteById (2)).thenReturn (true);
        Result<WorldStats> result=service.deleteById (2);
        assertEquals (ResultType.SUCCESS,result.getType ());
    }
    @Test
    void shouldNotDeleteById() {
        when(repository.deleteById (20)).thenReturn (false);
        Result<WorldStats> result=service.deleteById (20);
        assertEquals (ResultType.NOT_FOUND,result.getType ());
        assertEquals(String.format("worldStats id: %s, not found.", 20), result.getMessages().get(0));
    }
    WorldStats makeWorldStats(){
        WorldStats worldStats = new WorldStats ();
        worldStats.setWorldStatsId (1);
        worldStats.setPlayerCharacterId (1);
        worldStats.setEnemiesKilled (50);
        worldStats.setItemsUsed (10);
        worldStats.setTimesDied (5);
        return worldStats;
    }
}