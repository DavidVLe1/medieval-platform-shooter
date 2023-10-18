package learn.platformShooter.data.mappers;

import learn.platformShooter.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i)throws SQLException {
        User user = new User ();
        user.setUserId (resultSet.getInt ("user_id"));
        user.setFirstName (resultSet.getString ("first_name"));
        user.setLastName (resultSet.getString ("last_name"));
        user.setUsername (resultSet.getString ("username"));
        user.setPassword (resultSet.getString ("password"));
        user.setEmail (resultSet.getString ("email"));
        user.setFavoriteColor (resultSet.getString ("favorite_color"));
        user.setGender (resultSet.getString ("gender"));
        return user;
    }


}
