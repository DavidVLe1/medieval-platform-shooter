package learn.platformShooter.controllers;

import learn.platformShooter.domain.ItemService;
import learn.platformShooter.models.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> findAll() {
        return itemService.findAll ();
    }

    @GetMapping("/{itemType}")
    public List<Item> findByType(@PathVariable String itemType) {
        return itemService.findByType (itemType);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Item> findById(@PathVariable int itemId) {
        Item item = itemService.findById (itemId);
        if (item == null) {
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok (item);
    }

    //implement create
    //implement update
    //implement delete

}