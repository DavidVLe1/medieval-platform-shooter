package learn.platformShooter.data;

import learn.platformShooter.data.mappers.ItemMapper;
import learn.platformShooter.models.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
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
        final String sql = "select item_id,name, item_description, type, stat_increment "
                +"from item "
                +"where type= ? ;";
        return jdbcTemplate.query (sql,new ItemMapper (),type);
    }

    @Override
    public Item findById(int itemId) {
        final String sql = "select item_id,name, item_description, type, stat_increment "
                +"from item "
                +"where item_id= ? ;";
        return jdbcTemplate.query (sql,new ItemMapper (), itemId).stream ().findFirst ().orElse (null);
    }

    @Override
    public Item add(Item item) {
        final String sql = "insert into item (name, item_description, type, stat_increment) "
                +"values (?,?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder ();
        int rowsAffected = jdbcTemplate.update (connection ->{
            PreparedStatement ps = connection.prepareStatement (sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString (1,item.getName ());
            ps.setString (2, item.getItemDescription ());
            ps.setString (3,item.getType ());
            ps.setDouble (4,item.getStatIncrement ());
            return ps;
        },keyHolder);
        if(rowsAffected<=0){
            return null;
        }
        item.setItemId (keyHolder.getKey ().intValue ());
        return item;
    }

    @Override
    public boolean update(Item item) {
        final String sql = "update item set "
                +"name = ?, "
                +"item_description = ?, "
                +"type = ?, "
                +"stat_increment = ? "
                +"where item_id= ?;";
        return jdbcTemplate.update (sql,
                item.getName (),
                item.getItemDescription (),
                item.getType (),
                item.getStatIncrement (),
                item.getItemId ())>0;
    }

    @Override
    @Transactional
    public boolean deleteById(int itemId) {
        return jdbcTemplate.update ("delete from item where item_id = ?;", itemId)>0;
    }
}
