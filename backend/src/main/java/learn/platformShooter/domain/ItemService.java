package learn.platformShooter.domain;

import learn.platformShooter.data.ItemRepository;
import learn.platformShooter.models.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository repository;
    public ItemService(ItemRepository repository){this.repository=repository;}

    public List<Item> findAll(){return repository.findAll ();}
    public List<Item> findByType(String type){return repository.findByType (type);}
    public Item findById(int itemId){return repository.findById (itemId);}
    public Result<Item> add(Item item){
        Result<Item> result= validate(item);

        if (!result.isSuccess()) {
            return result;
        }

        if (item.getItemId () != 0) {//meaning this executes if not zero
            result.addMessage("itemId cannot be set for `add` operation.", ResultType.INVALID);
            return result;
        }
        item = repository.add(item);
        result.setPayload(item);
        return result;
    }
    public Result<Item> update(Item item){
        Result<Item> result= validate(item);
        if (!result.isSuccess()) {
            return result;
        }

        if (item.getItemId () <= 0) {//meaning this executes if zero or under
            result.addMessage("itemId must be set for `update` operation.", ResultType.INVALID);
            return result;
        }
        if (!repository.update(item)) {
            String msg = String.format("itemId: %s, not found", item.getItemId ());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }
    public Result<Item> deleteById(int itemId){
        Result<Item> result = new Result<> ();
        if (!repository.deleteById(itemId)) {
            String msg = String.format("Item id: %s, not found.", itemId);
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    }

    private Result<Item> validate(Item item){
        Result<Item> result = new Result<> ();
        if (item == null) {
            result.addMessage("Item cannot be null.", ResultType.INVALID);
            return result;
        }
        if (Validations.isNullOrBlank(item.getName ())) {
            result.addMessage("Item Name is required.", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(item.getItemDescription ())) {
            result.addMessage("Item description is required.", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(item.getType ())) {
            result.addMessage("Item type is required.", ResultType.INVALID);
        }
        return result;
    }

}
