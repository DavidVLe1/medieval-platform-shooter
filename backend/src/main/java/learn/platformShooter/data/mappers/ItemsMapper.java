package learn.platformShooter.data.mappers;

import learn.platformShooter.models.Items;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemsMapper implements RowMapper<Items> {
    @Override
    public Items mapRow(ResultSet resultSet, int i)throws SQLException {
        Items items = new Items ();
        items.setItemId (resultSet.getInt ("item_id"));
        items.setName (resultSet.getString ("name"));
        items.setItemDescription (resultSet.getString ("item_description"));
        items.setType (resultSet.getString ("type"));
        items.setStatIncrement (resultSet.getDouble ("stat_increment"));
        return items;
    }
}
