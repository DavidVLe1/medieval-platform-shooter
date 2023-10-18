package learn.platformShooter.data;

import learn.platformShooter.models.Item;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ItemRepository {
    List<Item> findAll();
    List<Item> findByType(String type);
    Item findById(int itemId);
    Item add(Item items);
    boolean update(Item items);
    @Transactional
    boolean deleteById(int itemId);
}
