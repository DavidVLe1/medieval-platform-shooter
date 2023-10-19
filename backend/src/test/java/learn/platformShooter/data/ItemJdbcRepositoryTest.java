package learn.platformShooter.data;

import learn.platformShooter.models.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ItemJdbcRepositoryTest {
    @Autowired
    ItemJdbcRepository repository;
    @Autowired
    KnownGoodState knownGoodState;
    @BeforeEach
    void setup(){knownGoodState.set();}

    @Test
    void findAll() {
        List<Item> items = repository.findAll ();
        assertNotNull (items);
    }

    @Test
    void findByType() {
        List<Item> items = repository.findByType ("damage");
        assertNotNull (items);
        assertTrue (items.get(0).getType ().equals ("damage"));
    }

    @Test
    void findById() {
        Item item = repository.findById (2);
        assertNotNull (item);
        assertEquals (new Item (2,"Sword of Fire",
                "A blazing sword that deals fire damage.","damage",10 ),item);
    }

    @Test
    void add() {
        Item item = new Item (0,"Legendary_Blaster","A weapon from another world!","damage",50);
        Item actual = repository.add (item);
        assertNotNull (actual);
        assertEquals (5,actual.getItemId ());
    }

    @Test
    void update() {
        Item item = new Item (0,"Plasma Cannon","A gun that uses plasma energy!","damage",15);
        item.setItemId (3);
        assertTrue (repository.update(item));
    }

    @Test
    void deleteById() {
        assertTrue (repository.deleteById (5));
    }
}