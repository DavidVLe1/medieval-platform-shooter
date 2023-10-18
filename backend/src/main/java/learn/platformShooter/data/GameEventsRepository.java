package learn.platformShooter.data;

import learn.platformShooter.models.GameEvents;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GameEventsRepository {
    List<GameEvents> findAll();//not likely to be used as one instance of game events will be for one player character
    GameEvents findById(int gameEventsId);
    GameEvents add(GameEvents gameEvents);
    boolean update( GameEvents gameEvents);
    @Transactional
    boolean deleteById(int gameEventsId);
}
