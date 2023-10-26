package learn.platformShooter.data;

import learn.platformShooter.models.Auth;
import learn.platformShooter.models.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findById(int userId);
    User findByAuth(Auth userToAuth);

    User findByUsername(String username);

    User findByEmail(String email);

    User add(User user);
    boolean update(User user);
    @Transactional
    boolean deleteById(int userId);
}
