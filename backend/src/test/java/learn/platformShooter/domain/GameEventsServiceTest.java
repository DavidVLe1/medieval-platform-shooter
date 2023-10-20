package learn.platformShooter.domain;

import learn.platformShooter.data.GameEventsRepository;
import learn.platformShooter.models.GameEvents;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GameEventsServiceTest {
    @Autowired
    GameEventsService service;
    @MockBean
    GameEventsRepository repository;

    @Test
    void shouldFindAll() {
        GameEvents expected =makeGameEvents ();
        GameEvents expected2= makeGameEvents ();
        expected2.setGameEventsId (2);
        expected2.setPlayerCharacterId (2);
        expected2.setBossesKilled (1);
        expected2.setGameCompleted (false);
        when(repository.findAll ()).thenReturn (List.of (expected, expected2));
        assertEquals (service.findAll (),List.of (expected,expected2));
    }

    @Test
    void shouldFindById() {
        GameEvents expected = makeGameEvents ();
        when(repository.findById (1)).thenReturn (expected);
        GameEvents actual = service.findById (1);
        assertEquals (expected,actual);
        assertNull(service.findById (20));
    }

    @Test
    void shouldFindByPlayerCharacterId() {
        GameEvents expected = makeGameEvents ();
        when(repository.findByPlayerCharacterId (1)).thenReturn (expected);
        GameEvents actual = service.findByPlayerCharacterId (1);
        assertEquals (expected,actual);
        assertNull(service.findByPlayerCharacterId (20));
    }


    @Test
    void shouldAdd() {
        GameEvents gameEvents = makeGameEvents ();
        gameEvents.setGameEventsId (0);
        gameEvents.setGameCompleted (false);
        gameEvents.setLegendaryItemObtained (false);
        gameEvents.setBossesKilled (0);
        GameEvents expected = makeGameEvents ();
        expected.setGameEventsId (3);
        expected.setGameCompleted (false);
        expected.setLegendaryItemObtained (false);
        expected.setBossesKilled (0);
        when (repository.add (gameEvents)).thenReturn (expected);

        Result<GameEvents> result = service.add (gameEvents);
        assertEquals (ResultType.SUCCESS,result.getType ());
        assertEquals (expected,result.getPayload ());
    }

    @Test
    void shouldNotAdd() {
        //can't add null
        Result<GameEvents> result = service.add (null);
        assertEquals (ResultType.INVALID, result.getType ());

        // Should not add when gameEventsId is not 0
        GameEvents gameEvents = makeGameEvents ();
        gameEvents.setGameEventsId (1);
        gameEvents.setGameCompleted (false);
        gameEvents.setLegendaryItemObtained (false);
        gameEvents.setBossesKilled (0);
        result=service.add (gameEvents);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("gameEventsId cannot be set for `add` operation.", result.getMessages().get(0));

        // Should not add gameCompleted as true
        gameEvents = makeGameEvents ();
        gameEvents.setGameCompleted (true);
        gameEvents.setGameEventsId (0);
        gameEvents.setLegendaryItemObtained (false);
        gameEvents.setBossesKilled (0);
        result=service.add (gameEvents);
        assertEquals (ResultType.INVALID, result.getType ());
        assertEquals ("gameEvents GameCompleted cannot be true for `add` operation.", result.getMessages ().get (0));

        //Should not add LegendaryItem obtained as true.
        gameEvents = makeGameEvents ();
        gameEvents.setLegendaryItemObtained (true);
        gameEvents.setGameEventsId (0);
        gameEvents.setGameCompleted (false);
        gameEvents.setBossesKilled (0);
        result = service.add (gameEvents);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("gameEvents LegendaryItemObtained cannot be true for `add` operation.",result.getMessages ().get (0));

        //should not add when bossesKilled is not 0
        gameEvents=makeGameEvents ();
        gameEvents.setGameEventsId (0);
        gameEvents.setGameCompleted (false);
        gameEvents.setLegendaryItemObtained (false);
        gameEvents.setBossesKilled (2);
        result=service.add (gameEvents);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("gameEvents bossesKilled cannot be anything other than 0 for `add` operation.",result.getMessages ().get (0));

        //playerCharacterId cant be 0 and under.
        gameEvents = makeGameEvents ();
        gameEvents.setPlayerCharacterId (0);
        gameEvents.setGameEventsId (0);
        gameEvents.setGameCompleted (false);
        gameEvents.setLegendaryItemObtained (false);
        result=service.add (gameEvents);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("Player Character Id is required and must be more than 0.",result.getMessages ().get (0));

        //bossesKilled must not be negative .
        gameEvents = makeGameEvents ();
        gameEvents.setGameEventsId (0);
        gameEvents.setGameCompleted (false);
        gameEvents.setLegendaryItemObtained (false);
        gameEvents.setBossesKilled (-2);
        result=service.add (gameEvents);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("gameEvents bossesKilled can't be negative.",result.getMessages ().get (0));
    }
    @Test
    void shouldUpdate() {
        GameEvents gameEvents=makeGameEvents ();
        when(repository.update (gameEvents)).thenReturn (true);
        Result<GameEvents> actual = service.update (gameEvents);
        assertEquals (ResultType.SUCCESS,actual.getType ());
    }
    @Test
    void shouldNotUpdate() {
        //can't add null
        Result<GameEvents> result = service.update (null);
        assertEquals (ResultType.INVALID, result.getType ());

        // Should not update when gameEventsId is 0
        GameEvents gameEvents = makeGameEvents ();
        gameEvents.setGameEventsId (0);
        gameEvents.setGameCompleted (false);
        gameEvents.setLegendaryItemObtained (false);
        gameEvents.setBossesKilled (0);
        result=service.update (gameEvents);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("gameEventsId must be set for `update` operation.", result.getMessages().get(0));

        // Should not update when gameEvents not found
        gameEvents.setGameEventsId (20);
        when(repository.update(gameEvents)).thenReturn(false);
        result = service.update(gameEvents);
        assertEquals(ResultType.NOT_FOUND, result.getType());
        assertEquals(String.format("gameEvents: %s, not found.", 20), result.getMessages().get(0));


        //playerCharacterId cant be 0 and under.
        gameEvents = makeGameEvents ();
        gameEvents.setPlayerCharacterId (0);
        gameEvents.setGameEventsId (0);
        gameEvents.setGameCompleted (false);
        gameEvents.setLegendaryItemObtained (false);
        result=service.update (gameEvents);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("Player Character Id is required and must be more than 0.",result.getMessages ().get (0));

        //bossesKilled must not be negative.
        gameEvents = makeGameEvents ();
        gameEvents.setGameEventsId (0);
        gameEvents.setGameCompleted (false);
        gameEvents.setLegendaryItemObtained (false);
        gameEvents.setBossesKilled (-2);
        result=service.update (gameEvents);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("gameEvents bossesKilled can't be negative.",result.getMessages ().get (0));
    }
    @Test
    void shouldDeleteById() {
        GameEvents gameEvents = makeGameEvents ();
        gameEvents.setGameEventsId (2);
        when(repository.deleteById (2)).thenReturn (true);
        Result<GameEvents> result = service.deleteById (2);
        assertEquals (ResultType.SUCCESS,result.getType ());
    }

    @Test
    void shouldNotDeleteById() {
        GameEvents gameEvents = makeGameEvents ();
        gameEvents.setGameEventsId (20);
        when(repository.deleteById (20)).thenReturn (false);
        Result<GameEvents> result = service.deleteById (20);
        assertEquals (ResultType.NOT_FOUND,result.getType ());
        assertEquals(String.format("GameEvents id: %s, not found", 20), result.getMessages().get(0));
    }

    GameEvents makeGameEvents(){
        //game_events_id, player_character_id, bosses_killed, legendary_item_obtained, game_completed
        GameEvents gameEvents = new GameEvents ();
        gameEvents.setGameEventsId (1);
        gameEvents.setPlayerCharacterId (1);
        gameEvents.setBossesKilled (2);
        gameEvents.setLegendaryItemObtained (true);
        gameEvents.setGameCompleted (true);
        return gameEvents;
    }
}