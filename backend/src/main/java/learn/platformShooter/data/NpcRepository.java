package learn.platformShooter.data;

import learn.platformShooter.models.Npc;

import java.util.List;

public interface NpcRepository {
    List<Npc> findAll();
    Npc findById(int npcId);
    //don't need findByName for npc.
    Npc add(Npc npc);
    boolean update(Npc npc);
    boolean deleteById(int npcId);
}
