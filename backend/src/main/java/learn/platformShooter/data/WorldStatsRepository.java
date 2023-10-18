package learn.platformShooter.data;

import learn.platformShooter.models.WorldStats;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WorldStatsRepository {
    List<WorldStats> findAll();
    WorldStats findById(int worldStatsId);
    WorldStats findByPlayerId(int playerCharacterId);
    WorldStats add(WorldStats worldStats);
    boolean update(WorldStats worldStats);
    @Transactional
    boolean deleteById(int worldStatsId);
}
