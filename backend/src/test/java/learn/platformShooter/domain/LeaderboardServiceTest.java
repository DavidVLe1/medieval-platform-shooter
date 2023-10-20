package learn.platformShooter.domain;

import learn.platformShooter.data.LeaderboardRepository;
import learn.platformShooter.models.Leaderboard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class LeaderboardServiceTest {
    @Autowired
    LeaderboardService service;
    @MockBean
    LeaderboardRepository repository;

    @Test
    void shouldFindAll() {
        Leaderboard leaderboard1= new Leaderboard (1,1,"johndoe",300);
        Leaderboard leaderboard2= new Leaderboard (2,2,"janesmith",150);
        Leaderboard leaderboard3= new Leaderboard (4,4,"Marcee",250);

        when(repository.findAll ()).thenReturn (List.of (leaderboard1,leaderboard2,leaderboard3));
        assertEquals (List.of(leaderboard1,leaderboard2,leaderboard3),service.findAll ());
    }

    @Test
    void shouldFindById() {
        Leaderboard leaderboard = makeLeaderboard ();
        when(repository.findById (1)).thenReturn (leaderboard);
        Leaderboard actual = service.findById (1);
        assertEquals (leaderboard,actual);
        assertNull (service.findById (20));
    }

    @Test
    void shouldAdd() {
        Leaderboard leaderboard = new Leaderboard (0,5,"jackiechan",0);
        Leaderboard expected = new Leaderboard (5,5,"jackiechan",0);

        when(repository.add (leaderboard)).thenReturn (expected);
        Result<Leaderboard> result = service.add (leaderboard);
        assertEquals (ResultType.SUCCESS,result.getType ());
        assertEquals (expected,result.getPayload ());
    }
    @Test
    void shouldNotAdd() {
        //cant add null
        Result<Leaderboard> result = service.add (null);
        assertEquals (ResultType.INVALID, result.getType ());

        //leaderboard leaderboardId must be 0 for adding.
        Leaderboard leaderboard = makeLeaderboard ();
        leaderboard.setScore (0);
        result=service.add (leaderboard);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("leaderboardId cannot be set for `add` operation.", result.getMessages ().get (0));
        //leaderboard score must be 0 for add.
        leaderboard = makeLeaderboard ();
        leaderboard.setScore (200);
        leaderboard.setLeaderboardId (0);
        result=service.add (leaderboard);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("leaderboard score must be 0 for `add` operation.", result.getMessages ().get (0));
        //leaderboard username is required
        leaderboard=makeLeaderboard ();
        leaderboard.setScore (0);
        leaderboard.setLeaderboardId (0);
        leaderboard.setUsername (null);
        result=service.add (leaderboard);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("Leaderboard username is required.", result.getMessages ().get (0));
        leaderboard=makeLeaderboard ();
        leaderboard.setScore (0);
        leaderboard.setLeaderboardId (0);
        leaderboard.setUsername ("");
        result=service.add (leaderboard);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("Leaderboard username is required.", result.getMessages ().get (0));
        //leaderboard userId is required
        leaderboard=makeLeaderboard ();
        leaderboard.setScore (0);
        leaderboard.setLeaderboardId (0);
        leaderboard.setUserId (0);
        result=service.add (leaderboard);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("Leaderboard userId cannot be 0 and under.", result.getMessages ().get (0));
        //leaderboard score cannot be negative.
        leaderboard=makeLeaderboard ();
        leaderboard.setScore (-200);
        leaderboard.setLeaderboardId (0);
        result=service.add (leaderboard);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("Leaderboard score cannot be less than 0.", result.getMessages ().get (0));
    }

    @Test
    void shouldUpdate() {
        Leaderboard leaderboard = makeLeaderboard ();
        when(repository.update (leaderboard)).thenReturn (true);
        Result<Leaderboard> result = service.update (leaderboard);
        assertEquals (ResultType.SUCCESS,result.getType ());
    }
    @Test
    void shouldNotUpdate() {
        //can't update null
        Result<Leaderboard> result=service.update (null);
        assertEquals (ResultType.INVALID,result.getType ());

        //Leaderboard leaderboardID must be set.
        Leaderboard leaderboard=makeLeaderboard ();
        leaderboard.setLeaderboardId (0);
        result=service.update (leaderboard);
        assertEquals (ResultType.INVALID,result.getType());
        assertEquals ("leaderboardId must be set for `update` operation.",result.getMessages ().get (0));
        //can't update missing leaderboardId
        leaderboard=makeLeaderboard ();
        leaderboard.setLeaderboardId (20);
        when(repository.update (leaderboard)).thenReturn (false);
        result=service.update (leaderboard);
        assertEquals (ResultType.NOT_FOUND,result.getType ());
        assertEquals (String.format("leaderboardId: %s, not found.",20),result.getMessages ().get (0));

        //leaderboard username is required
        leaderboard=makeLeaderboard ();
        leaderboard.setScore (0);
        leaderboard.setLeaderboardId (0);
        leaderboard.setUsername (null);
        result=service.update (leaderboard);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("Leaderboard username is required.", result.getMessages ().get (0));
        leaderboard=makeLeaderboard ();
        leaderboard.setScore (0);
        leaderboard.setLeaderboardId (0);
        leaderboard.setUsername ("");
        result=service.update (leaderboard);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("Leaderboard username is required.", result.getMessages ().get (0));
        //leaderboard userId is required
        leaderboard=makeLeaderboard ();
        leaderboard.setScore (0);
        leaderboard.setLeaderboardId (0);
        leaderboard.setUserId (0);
        result=service.update (leaderboard);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("Leaderboard userId cannot be 0 and under.", result.getMessages ().get (0));
        //leaderboard score cannot be negative.
        leaderboard=makeLeaderboard ();
        leaderboard.setScore (-200);
        leaderboard.setLeaderboardId (0);
        result=service.update (leaderboard);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("Leaderboard score cannot be less than 0.", result.getMessages ().get (0));
    }

    @Test
    void shouldDeleteById() {
        when(repository.deleteById (1)).thenReturn (true);
        Result<Leaderboard> result = service.deleteById (1);
        assertEquals (ResultType.SUCCESS,result.getType ());
    }
    @Test
    void shouldNotDeleteById() {

        when(repository.deleteById (20)).thenReturn (false);
        Result<Leaderboard> result = service.deleteById (20);
        assertEquals (ResultType.NOT_FOUND,result.getType ());
        assertEquals (String.format("leaderboard id: %s, not found", 20),result.getMessages ().get (0));
    }
    Leaderboard makeLeaderboard(){
        Leaderboard leaderboard = new Leaderboard ();
        leaderboard.setLeaderboardId (1);
        leaderboard.setUserId (1);
        leaderboard.setUsername ("johndoe");
        leaderboard.setScore (300);
        return leaderboard;
    }

}