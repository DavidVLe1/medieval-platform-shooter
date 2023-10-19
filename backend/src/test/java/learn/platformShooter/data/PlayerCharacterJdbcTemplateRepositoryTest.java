package learn.platformShooter.data;

import learn.platformShooter.models.PlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PlayerCharacterJdbcTemplateRepositoryTest {
    @Autowired
    PlayerCharacterJdbcTemplateRepository repository;
    @Autowired
    KnownGoodState knownGoodState;
    @BeforeEach
    void setup(){knownGoodState.set();}

    @Test
    void shouldFindAll() {
        List<PlayerCharacter> actual = repository.findAll ();
        assertNotNull (actual);
    }

    @Test
    void shouldFindByUserId() {
        PlayerCharacter actual = repository.findByUserId (2);
        PlayerCharacter expected = new PlayerCharacter (2,2, 2700, 8,80, 70, 10, 6, 3);
        assertNotNull (actual);
        assertEquals (expected,actual);
    }

    @Test
    void shouldFindByPlayerId() {
        PlayerCharacter actual = repository.findByPlayerId (1);
        PlayerCharacter expected = new PlayerCharacter (1,1, 3600, 10.5,100, 100, 15, 8, 5);
        assertNotNull (actual);
        assertEquals (expected,actual);
    }

    @Test
    void shouldAdd() {
        PlayerCharacter newPlayerCharacter = new PlayerCharacter (4,1,0,1,50,50,10,4,5);
        PlayerCharacter actual = repository.add (newPlayerCharacter);
        assertNotNull (actual);
        assertEquals (newPlayerCharacter,actual);
    }

    @Test
    void shouldUpdate() {
        PlayerCharacter replacedPlayer1 = new PlayerCharacter (1,1,0,1,50,50,10,4,5);
        assertTrue (repository.update (replacedPlayer1));
        assertEquals (replacedPlayer1, repository.findByPlayerId (1));
    }

    @Test
    void shouldDeleteById() {//run test with all the tests.
        assertTrue (repository.deleteById (3));
    }
}