package learn.platformShooter.data;

import learn.platformShooter.data.mappers.ItemMapper;
import learn.platformShooter.models.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemJdbcRepository implements ItemRepository {
    private final JdbcTemplate jdbcTemplate;
    public ItemJdbcRepository(JdbcTemplate jdbcTemplate){this.jdbcTemplate=jdbcTemplate;}

    @Override
    public List<Item> findAll() {
        final String sql = "select item_id,name, item_description, type, stat_increment "
                +"from item;";
        return jdbcTemplate.query (sql,new ItemMapper ());
    }

    @Override
    public List<Item> findByType(String type) {
        return null;
    }

    @Override
    public Item findById(int itemId) {
        return null;
    }

    @Override
    public Item add(Item items) {
        return null;
    }

    @Override
    public boolean update(Item items) {
        return false;
    }

    @Override
    public boolean deleteById(int itemId) {
        return false;
    }
}
