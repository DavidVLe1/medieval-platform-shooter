package learn.platformShooter.data;

import learn.platformShooter.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserJdbcTemplateRepositoryTest {

    @Autowired
    UserJdbcTemplateRepository repository;
    @Autowired
    KnownGoodState knownGoodState;
    @BeforeEach
    void setup(){knownGoodState.set();}

    @Test
    void findAll() {
        List<User> listOfUsers = repository.findAll ();
        assertNotNull (listOfUsers);
    }

    @Test
    void shouldFindById() {
        User actual = repository.findById (1);
        User expected = new User (1,"John", "Doe", "johndoe", "johndoe@example.com", "password123", "blue", "male");
        assertNotNull (actual);
        assertEquals (expected,actual);
    }

    @Test
    void shouldFindByEmail() {
        User actual = repository.findByEmail ("janesmith@example.com");
        assertNotNull (actual);
    }

    @Test
    void shouldAdd() {
        User newUser = new User ("Finn", "Mertins", "FinnTheHero", "FinnTheHero@example.com", "adventure", "blue", "male" );
        User actual = repository.add (newUser);
        assertNotNull (actual);
        assertEquals (5,actual.getUserId ());
    }

    @Test
    void shouldUpdate() {
        User changedUser = new User ("Jake", "Dog", "Jdawg", "Jdawg@example.com", "adventure", "yellow", "male" );
        changedUser.setUserId (1);
        assertTrue (repository.update (changedUser));
    }

    @Test
    void deleteById() {//test with everything

        assertTrue (repository.deleteById (5));
    }
}