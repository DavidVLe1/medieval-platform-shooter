package learn.platformShooter.domain;

import learn.platformShooter.data.ItemRepository;
import learn.platformShooter.models.GameEvents;
import learn.platformShooter.models.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ItemServiceTest {
    @Autowired
    ItemService service;
    @MockBean
    ItemRepository repository;

    @Test
    void shouldFindByType() {
        Item item = makeItem ();
        when(repository.findByType ("healing_potions")).thenReturn (List.of (item));
        List<Item> actual = service.findByType ("healing_potions");
        assertEquals (List.of(item),actual);
    }

    @Test
    void shouldFindById() {
        Item item = makeItem ();
        when(repository.findById (1)).thenReturn (item);
        Item actual = service.findById (1);
        assertEquals (item,actual);
        assertNull(service.findById (20));
    }

    @Test
    void shouldAdd() {
        Item item = new Item (0,"Diamond Armor", "Armor made of Diamond material", "max_health",50);
        Item expected = new Item (5,"Diamond Armor", "Armor made of Diamond material", "max_health",50);
        when(repository.add (item)).thenReturn (expected);
        Result<Item> result = service.add (item);
        assertEquals (ResultType.SUCCESS,result.getType ());
        assertEquals (expected ,result.getPayload ());
    }
    @Test
    void shouldNotAdd() {
        //can't add null
        Result<Item> result = service.add (null);
        assertEquals (ResultType.INVALID, result.getType ());
        // Should not add when gameEventsId is not 0
        Item item = makeItem ();
        result=service.add (item);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("itemId cannot be set for `add` operation.", result.getMessages().get(0));
        //Item name is required
        item = makeItem ();
        item.setItemId (0);
        item.setName (null);
        result=service.add (item);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Item Name is required.", result.getMessages().get(0));

        item = makeItem ();
        item.setItemId (0);
        item.setName ("");
        result=service.add (item);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Item Name is required.", result.getMessages().get(0));
        //Item description required
        item = makeItem ();
        item.setItemId (0);
        item.setItemDescription (null);
        result=service.add (item);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Item description is required.", result.getMessages().get(0));
        item = makeItem ();
        item.setItemId (0);
        item.setItemDescription ("");
        result=service.add (item);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Item description is required.", result.getMessages().get(0));
        //Item Type is required
        item = makeItem ();
        item.setItemId (0);
        item.setType ("");
        result=service.add (item);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Item type is required.", result.getMessages().get(0));
        item = makeItem ();
        item.setItemId (0);
        item.setType (null);
        result=service.add (item);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Item type is required.", result.getMessages().get(0));

    }

    @Test
    void shouldUpdate() {
        Item item = makeItem ();
        item.setName ("Super Health Potion");
        item.setStatIncrement (100);
        when(repository.update (item)).thenReturn (true);
        Result<Item> actual = service.update (item);
        assertEquals (ResultType.SUCCESS,actual.getType ());
    }
    @Test
    void shouldNotUpdate() {
        //can't update null;
        Result<Item> result = service.update (null);
        assertEquals (ResultType.INVALID, result.getType ());
        //can't update when itemId is 0.
        Item item = makeItem ();
        item.setItemId (0);
        result=service.update (item);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("itemId must be set for `update` operation.", result.getMessages().get(0));
        //can't update non-exiting item id
        item = makeItem ();
        item.setItemId (20);
        when(repository.update(item)).thenReturn(false);
        result=service.update (item);
        assertEquals (ResultType.NOT_FOUND,result.getType ());
        assertEquals (String.format("itemId: %s, not found", 20),result.getMessages ().get (0));
        //Item name is required
        item = makeItem ();
        item.setItemDescription ("this is nothing");
        item.setName (null);
        result=service.update (item);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Item Name is required.", result.getMessages().get(0));

        item = makeItem ();
        item.setName ("");
        result=service.update (item);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Item Name is required.", result.getMessages().get(0));
        //Item description required
        item = makeItem ();
        item.setItemDescription (null);
        item.setName ("Super Potion");
        result=service.update (item);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Item description is required.", result.getMessages().get(0));
        item = makeItem ();
        item.setItemDescription ("");
        item.setName ("Super Potion");
        result=service.update (item);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Item description is required.", result.getMessages().get(0));
        //Item Type is required
        item = makeItem ();
        item.setType ("");
        result=service.update (item);
        item.setName ("Super Potion");
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Item type is required.", result.getMessages().get(0));
        item = makeItem ();
        item.setType (null);
        item.setName ("Super Potion");
        result=service.update (item);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Item type is required.", result.getMessages().get(0));

    }

    @Test
    void shouldDeleteById() {
        Item item = makeItem ();
        item.setItemId (1);
        when(repository.deleteById (1)).thenReturn (true);
        Result<Item> result = service.deleteById (1);
        assertEquals (ResultType.SUCCESS,result.getType());

    }
    @Test
    void shouldNotDeleteById() {
        Item item = makeItem ();
        item.setItemId (20);
        when(repository.deleteById (20)).thenReturn (false);
        Result<Item> result = service.deleteById (20);
        assertEquals (ResultType.NOT_FOUND,result.getType());
        assertEquals(String.format("Item id: %s, not found.", 20), result.getMessages().get(0));
    }
    Item makeItem(){
        //item_id, name, item_description, type, stat_increment
        Item item = new Item ();
        item.setItemId (1);
        item.setName ("Health Potion");
        item.setItemDescription ("Restores health when consumed.");
        item.setType ("healing_potions");
        item.setStatIncrement (20.0);
        return item;
    }
}