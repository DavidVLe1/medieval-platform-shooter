package learn.platformShooter.data;

import learn.platformShooter.models.GameEvents;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GameEventsJdbcTemplateRepositoryTest {
    @Autowired
    GameEventsJdbcTemplateRepository repository;
    @Autowired
    KnownGoodState knownGoodState;
    @BeforeEach
    void setup(){knownGoodState.set();}

    @Test
    void shouldFindAll() {
        List<GameEvents> listOfAllPlayersEvents = repository.findAll ();
        assertNotNull (listOfAllPlayersEvents);
    }

    @Test
    void shouldFindById() {
        GameEvents events1=repository.findById (1);
        assertNotNull (events1);
    }
    @Test
    void shouldFindByPlayerCharacterId(){
        GameEvents eventsByPlayer1= repository.findByPlayerCharacterId (1);
        assertNotNull (eventsByPlayer1);
        assertEquals (1,eventsByPlayer1.getPlayerCharacterId ());
    }
    @Test
    void shouldAdd() {
        GameEvents newGameEvents = new GameEvents (3,0,false,false);//meaning new character has been made and instantiated with fresh gameEvent.
        GameEvents actual = repository.add (newGameEvents);
        assertNotNull (actual);
        assertEquals (3,actual.getGameEventsId ());
    }

    @Test
    void shouldUpdate() {
        GameEvents secondPlayerEvents=repository.findById (2);
        secondPlayerEvents.setLegendaryItemObtained (true);
        secondPlayerEvents.setGameCompleted (false);
        assertTrue (repository.update (secondPlayerEvents));
        assertTrue (repository.findById (2).isLegendaryItemObtained ());
        assertFalse (repository.findById (2).isGameCompleted ());
    }

    @Test
    void shouldDeleteById() {
        assertTrue (repository.deleteById (3));
    }
}