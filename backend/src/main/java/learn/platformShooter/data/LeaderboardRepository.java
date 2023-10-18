package learn.platformShooter.data;

import learn.platformShooter.models.Leaderboard;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LeaderboardRepository {
    List<Leaderboard> findAll();

    Leaderboard findById(int leaderboard_id);

    Leaderboard add(Leaderboard leaderboard);

    boolean update(Leaderboard leaderboard);

    @Transactional
    boolean deleteById(int leaderboard_id);
}
