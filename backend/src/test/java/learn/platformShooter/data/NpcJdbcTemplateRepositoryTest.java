package learn.platformShooter.data;

import learn.platformShooter.models.Npc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class NpcJdbcTemplateRepositoryTest {
    @Autowired
    NpcJdbcTemplateRepository repository;
    @Autowired
    KnownGoodState knownGoodState;
    @BeforeEach
    void setup(){knownGoodState.set();}

    @Test
    void findAll() {
        List<Npc> npcs = repository.findAll ();
        assertNotNull (npcs);
    }

    @Test
    void findById() {
        Npc actual = repository.findById (1);
        Npc expected = new Npc (1,"Vendor","Health",10.0);
        assertEquals (expected,actual);
    }

    @Test
    void add() {
        Npc newNpc = new Npc (0,"Vendor","Health",10.0);
        Npc actual = repository.add (newNpc);
        assertEquals (3,actual.getNpcId ());
    }

    @Test
    void update() {
        Npc expected = new Npc (2,"Shoe Vendor","speed",5.0);
        assertTrue (repository.update (expected));
    }

    @Test
    void deleteById() {
        assertTrue (repository.deleteById (3));
    }
}