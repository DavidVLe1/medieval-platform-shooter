package learn.platformShooter.data;

import learn.platformShooter.models.Leaderboard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class LeaderboardJdbcTemplateRepositoryTest {
    @Autowired
    LeaderboardJdbcTemplateRepository repository;
    @Autowired
    KnownGoodState knownGoodState;
    @BeforeEach
    void setup(){knownGoodState.set();}

    @Test
    void shouldFindAllAndSortByDesc() {//sorted score desc
        List<Leaderboard> topPlayers = repository.findAll ();
        assertNotNull (topPlayers);
        int number1Player=topPlayers.get (0).getScore ();
        int number2Player=topPlayers.get (1).getScore ();
        assertTrue (number1Player>number2Player);
    }

    @Test
    void shouldFindById() {
        Leaderboard player2 =repository.findById (2);
        Leaderboard expected = new Leaderboard (2,2,"janesmith",150);
        assertNotNull (player2);
        assertEquals (expected,player2);
    }

    @Test
    void shouldAdd() {
        Leaderboard newLeaderboard = new Leaderboard (0,4,"Marcee",250);
        Leaderboard actual = repository.add (newLeaderboard);
        assertNotNull (actual);
        assertEquals (4,actual.getLeaderboardId ());
    }

    @Test
    void shouldUpdate() {
        Leaderboard replaceScorer= repository.findById (1);
        replaceScorer.setScore (300);
        assertTrue (repository.update (replaceScorer));
        Leaderboard replaced = repository.findById (1);
        assertEquals (replaceScorer,replaced);
    }

    @Test
    void shouldDeleteById() {
        assertTrue (repository.deleteById (3));
    }
}