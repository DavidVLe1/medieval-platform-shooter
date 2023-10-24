package learn.platformShooter.controllers;

import learn.platformShooter.domain.ItemService;
import learn.platformShooter.domain.Result;
import learn.platformShooter.models.Item;
import learn.platformShooter.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Item item){
        Result<Item> result = itemService.add (item);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }
    //implement update
    @PutMapping("/{itemId}")
    public ResponseEntity<Object> update(@PathVariable int itemId,@RequestBody Item item) {
        if (itemId != item.getItemId ()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Result<Item> result = itemService.update (item);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }
    //implement delete
    @DeleteMapping("/{itemId}")
    public ResponseEntity<User> deleteById(@PathVariable int itemId) {
        Result<Item> result =itemService.deleteById (itemId);
        if (result.isSuccess ()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}