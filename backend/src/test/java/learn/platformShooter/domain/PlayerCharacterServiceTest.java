package learn.platformShooter.domain;

import learn.platformShooter.data.PlayerCharacterRepository;
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
    }
    @Test
    void ShouldNotAdd() {
    }

    @Test
    void ShouldUpdate() {
    }
    @Test
    void ShouldNotUpdate() {
    }

    @Test
    void ShouldDeleteById() {
    }
    @Test
    void ShouldNotDeleteById() {
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