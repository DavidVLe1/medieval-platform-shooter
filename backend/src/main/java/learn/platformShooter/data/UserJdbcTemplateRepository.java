package learn.platformShooter.data;

import learn.platformShooter.data.mappers.UserMapper;
import learn.platformShooter.models.Auth;
import learn.platformShooter.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserJdbcTemplateRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcTemplateRepository(JdbcTemplate jdbcTemplate){this.jdbcTemplate=jdbcTemplate;}

    @Override
    public List<User> findAll() {
        final String sql = "select user_id,first_name, last_name, username, email, password, favorite_color, gender "
                + "from `user`;";
        return jdbcTemplate.query (sql,new UserMapper ());
    }

    @Override
    public User findById(int userId) {
        final String sql = "select user_id, first_name, last_name, username, email, password, favorite_color, gender "
                +"from `user` "
                +"where user_id = ?;";
        return jdbcTemplate.query (sql, new UserMapper(), userId).stream ().findFirst ().orElse (null);
    }
    @Override
    public User findByUsername(String username) {
        final String sql = "select user_id, first_name, last_name, username, email, password, favorite_color, gender "
                +"from `user` "
                +"where username = ?;";
        return jdbcTemplate.query (sql, new UserMapper(), username).stream ().findFirst ().orElse (null);
    }

    @Override
    public User findByEmail(String email){
        final String sql = "select user_id,first_name, last_name, username, email, password, favorite_color, gender "
                +"from `user` "
                +"where email = ?;";
        return jdbcTemplate.query (sql, new UserMapper(), email).stream ().findFirst ().orElse (null);
    }
    @Override
    @Transactional
    public User findByAuth(Auth userToAuth) {

        final String sql = "select user_id, first_name, last_name, username,email, password, favorite_color, gender  "
                + "from user "
                + "where email = ? and password = ?;";

        return jdbcTemplate.query(sql, new UserMapper(), userToAuth.getEmail(), userToAuth.getPassword()).stream()
                .findFirst().orElse(null);

    }
    @Override
    public User add(User user) {
        final String sql = "insert into user (first_name, last_name, username, email, password, favorite_color, gender) "
                +"values (?,?,?,?,?,?,?);";
        KeyHolder keyHolder= new GeneratedKeyHolder ();
        int rowsAffected = jdbcTemplate.update (connection ->{
            PreparedStatement ps = connection.prepareStatement (sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString (1, user.getFirstName ());
            ps.setString (2, user.getLastName ());
            ps.setString (3, user.getUsername ());
            ps.setString (4, user.getEmail ());
            ps.setString (5,user.getPassword ());
            ps.setString (6,user.getFavoriteColor ());
            ps.setString (7,user.getGender ());
            return ps;
        },keyHolder);

        if(rowsAffected<=0){
            return null;
        }
        user.setUserId (keyHolder.getKey ().intValue ());
        return user;
    }

    @Override
    public boolean update(User user) {
        final String sql = "update user set "
                + "first_name = ?, "
                + "last_name = ?, "
                + "username = ?, "
                + "email = ?, "
                + "password = ?, "
                + "favorite_color = ?, "
                + "gender = ? "
                + "where user_id = ?;";

        return jdbcTemplate.update (sql,
                user.getFirstName (),
                user.getLastName (),
                user.getUsername (),
                user.getEmail (),
                user.getPassword (),
                user.getFavoriteColor (),
                user.getGender (),
                user.getUserId ())>0;
    }

    @Override
    @Transactional
    public boolean deleteById(int userId) {
        return jdbcTemplate.update ("delete from user where user_id = ?;", userId)>0;
    }
}
