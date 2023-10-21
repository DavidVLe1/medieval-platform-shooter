package learn.platformShooter.domain;

import learn.platformShooter.data.PlayerCharacterRepository;
import learn.platformShooter.models.GameEvents;
import learn.platformShooter.models.PlayerCharacter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PlayerCharacterServiceTest {
    @Autowired
    PlayerCharacterService service;
    @MockBean
    PlayerCharacterRepository repository;

    @Test
    void ShouldFindAll() {
        PlayerCharacter playerCharacter1 = new PlayerCharacter (1,1,3600,10.5,100,100,15,8,5);
        PlayerCharacter playerCharacter2 = new PlayerCharacter (2,2,2700,8,80,70,10,6,3);
        PlayerCharacter playerCharacter3 = new PlayerCharacter (3,3,5400,12.5,120,90,18,7,1);
        List<PlayerCharacter> playerCharacterList=List.of (playerCharacter1,playerCharacter2,playerCharacter3);
        when(repository.findAll ()).thenReturn (playerCharacterList);
        assertEquals (playerCharacterList,service.findAll ());
    }

    @Test
    void ShouldFindByUserId() {
        PlayerCharacter playerCharacter = makePlayerCharacter ();
        when (repository.findByUserId (1)).thenReturn (playerCharacter);
        PlayerCharacter actual = service.findByUserId (1);
        assertEquals (playerCharacter,actual);
        assertNull (service.findByUserId (20));
    }
    @Test
    void ShouldFindByPlayerId() {
        PlayerCharacter playerCharacter = makePlayerCharacter ();
        when (repository.findByPlayerId (1)).thenReturn (playerCharacter);
        PlayerCharacter actual = service.findByPlayerId (1);
        assertEquals (playerCharacter,actual);
        assertNull (service.findByPlayerId (20));
    }

    @Test
    void ShouldAdd() {
        PlayerCharacter playerCharacter = makePlayerCharacter ();
        playerCharacter.setCharactersLevel (1);
        playerCharacter.setTimePlayedInSeconds (0);
        playerCharacter.setPlayerCharacterId (0);
        PlayerCharacter expected = makePlayerCharacter ();
        expected.setCharactersLevel (1);
        expected.setTimePlayedInSeconds (0);
        expected.setPlayerCharacterId (4);
        when (repository.add (playerCharacter)).thenReturn (expected);
        Result<PlayerCharacter> result = service.add (playerCharacter);
        assertEquals (ResultType.SUCCESS,result.getType ());
        assertEquals (expected,result.getPayload ());
    }
    @Test
    void ShouldNotAdd() {
        //can't add null
        Result<PlayerCharacter> result = service.add (null);
        assertEquals (ResultType.INVALID, result.getType ());
        //can't add when playerCharacterId is set.
        PlayerCharacter playerCharacter =new PlayerCharacter (1,1,0,1.0,100.0,100.0,15.0,8.0,5);
        result=service.add (playerCharacter);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("playerCharacterId cannot be set for `add` operation.", result.getMessages().get(0));
        //Can't add when time is set.
        playerCharacter =new PlayerCharacter (0,1,3600,1.0,100.0,100.0,15.0,8.0,5);
        result=service.add (playerCharacter);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("timePlayedInSeconds must be 0 for `add` operation.", result.getMessages().get(0));
        //playerCharacter Level must be 1 when adding a playerCharacter.
        playerCharacter =new PlayerCharacter (0,1,0,4.0,100.0,100.0,15.0,8.0,5);
        result=service.add (playerCharacter);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("charactersLevel must be 1 for `add` operation.", result.getMessages().get(0));
        //can't add when userId is 0.
        playerCharacter =new PlayerCharacter (0,0,0,1.0,100.0,100.0,15.0,8.0,5);
        result=service.add (playerCharacter);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("playerCharacter userId is required to be more than 0.", result.getMessages().get(0));
        //can't add when time
        playerCharacter =new PlayerCharacter (0,1,-10,1.0,100.0,100.0,15.0,8.0,5);
        result=service.add (playerCharacter);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("playerCharacter timePlayedInSeconds can't be negative.", result.getMessages().get(0));
        //playerCharacter level can't be less than 1 when adding.
        playerCharacter =new PlayerCharacter (0,1,0,-12.0,100.0,100.0,15.0,8.0,5);
        result=service.add (playerCharacter);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("playerCharacter level can't be less than 1 when adding.", result.getMessages().get(0));
        //playerCharacter maxHealth must be positive.
        playerCharacter =new PlayerCharacter (0,1,0,1.0,-100.0,100.0,15.0,8.0,5);
        result=service.add (playerCharacter);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("playerCharacter maxHealth is required to be more than 0.", result.getMessages().get(0));
        //playerCharacter damage must be positive.
        playerCharacter =new PlayerCharacter (0,1,0,1.0,100.0,100.0,-15.0,8.0,5);
        result=service.add (playerCharacter);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("playerCharacter damage is required to be more than 0.", result.getMessages().get(0));
        //playerCharacter speed must be positive.
        playerCharacter =new PlayerCharacter (0,1,0,1.0,100.0,100.0,15.0,-8.0,5);
        result=service.add (playerCharacter);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("playerCharacter speed is required to be more than 0.", result.getMessages().get(0));
        //playerCharacter healingPotions can't be negative.
        playerCharacter =new PlayerCharacter (0,1,0,1.0,100.0,100.0,15.0,8.0,-5);
        result=service.add (playerCharacter);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("playerCharacter healingPotions is required to be more than or equal to 0.", result.getMessages().get(0));
    }

    @Test
    void ShouldUpdate() {
        PlayerCharacter playerCharacter = makePlayerCharacter ();
        when(repository.update (playerCharacter)).thenReturn (true);
        Result<PlayerCharacter> actual = service.update (playerCharacter);
        assertEquals (ResultType.SUCCESS,actual.getType ());
    }
    @Test
    void ShouldNotUpdate() {
        //can't update null
        Result<PlayerCharacter> result = service.update (null);
        assertEquals (ResultType.INVALID, result.getType ());
        //should not update when playerCharacterId is 0.
        PlayerCharacter playerCharacter = new PlayerCharacter (0,1,100,1.0,100.0,100.0,15.0,8.0,5);
        result=service.update (playerCharacter);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("playerCharacterId must be set for `update` operation.", result.getMessages().get(0));
        //should not update when id not found.
        playerCharacter = new PlayerCharacter (1,1,100,1.0,100.0,100.0,15.0,8.0,5);
        playerCharacter.setPlayerCharacterId (20);
        when(repository.update(playerCharacter)).thenReturn(false);
        result = service.update(playerCharacter);
        assertEquals(ResultType.NOT_FOUND, result.getType());
        assertEquals(String.format("playerCharacterId: %s, not found.", 20), result.getMessages().get(0));


        //can't update when userId is 0.
        playerCharacter =new PlayerCharacter (1,0,0,1.0,100.0,100.0,15.0,8.0,5);
        result=service.update (playerCharacter);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("playerCharacter userId is required to be more than 0.", result.getMessages().get(0));
        //can't update when time
        playerCharacter =new PlayerCharacter (1,1,-10,1.0,100.0,100.0,15.0,8.0,5);
        result=service.update (playerCharacter);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("playerCharacter timePlayedInSeconds can't be negative.", result.getMessages().get(0));
        //playerCharacter level can't be less than 1 when adding.
        playerCharacter =new PlayerCharacter (1,1,0,-12.0,100.0,100.0,15.0,8.0,5);
        result=service.update (playerCharacter);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("playerCharacter level can't be less than 1 when adding.", result.getMessages().get(0));
        //playerCharacter maxHealth must be positive.
        playerCharacter =new PlayerCharacter (1,1,0,1.0,-100.0,100.0,15.0,8.0,5);
        result=service.update (playerCharacter);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("playerCharacter maxHealth is required to be more than 0.", result.getMessages().get(0));
        //playerCharacter damage must be positive.
        playerCharacter =new PlayerCharacter (1,1,0,1.0,100.0,100.0,-15.0,8.0,5);
        result=service.update (playerCharacter);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("playerCharacter damage is required to be more than 0.", result.getMessages().get(0));
        //playerCharacter speed must be positive.
        playerCharacter =new PlayerCharacter (1,1,0,1.0,100.0,100.0,15.0,-8.0,5);
        result=service.update (playerCharacter);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("playerCharacter speed is required to be more than 0.", result.getMessages().get(0));
        //playerCharacter healingPotions can't be negative.
        playerCharacter =new PlayerCharacter (1,1,0,1.0,100.0,100.0,15.0,8.0,-5);
        result=service.update (playerCharacter);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("playerCharacter healingPotions is required to be more than or equal to 0.", result.getMessages().get(0));
    }

    @Test
    void ShouldDeleteById() {
        when(repository.deleteById (4)).thenReturn (true);
        Result<PlayerCharacter> result = service.deleteById (4);
        assertEquals (ResultType.SUCCESS,result.getType ());
    }
    @Test
    void ShouldNotDeleteById() {
        when(repository.deleteById (20)).thenReturn (false);
        Result<PlayerCharacter> result = service.deleteById (20);
        assertEquals (ResultType.NOT_FOUND,result.getType ());
        assertEquals(String.format("playerCharacter id: %s, not found", 20), result.getMessages().get(0));
    }
    PlayerCharacter makePlayerCharacter(){
        PlayerCharacter playerCharacter = new PlayerCharacter ();
        playerCharacter.setPlayerCharacterId (1);
        playerCharacter.setUserId (1);
        playerCharacter.setTimePlayedInSeconds (3600);
        playerCharacter.setCharactersLevel (10.5);
        playerCharacter.setMaxHealth (100);
        playerCharacter.setHealth (100);
        playerCharacter.setDamage (15);
        playerCharacter.setSpeed (8);
        playerCharacter.setHealingPotions (5);
        return playerCharacter;
    }
}