package learn.platformShooter.data.mappers;

import learn.platformShooter.models.Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemMapper implements RowMapper<Item> {
    @Override
    public Item mapRow(ResultSet resultSet, int i)throws SQLException {
        Item items = new Item ();
        items.setItemId (resultSet.getInt ("item_id"));
        items.setName (resultSet.getString ("name"));
        items.setItemDescription (resultSet.getString ("item_description"));
        items.setType (resultSet.getString ("type"));
        items.setStatIncrement (resultSet.getDouble ("stat_increment"));
        return items;
    }
}
