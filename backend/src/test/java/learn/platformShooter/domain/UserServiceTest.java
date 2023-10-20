package learn.platformShooter.domain;

import learn.platformShooter.data.UserRepository;
import learn.platformShooter.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserServiceTest {
    @Autowired
    UserService service;
    @MockBean
    UserRepository repository;

    @Test
    void shouldFindAll() {
        User user1= makeUser ();
        User user2= new User (2,"Jane","Smith","janesmith","janesmith@example.com","mypassword","red","female");
        User user3= new User (3,"Cake","Cat","CakeCat","CakeCat@example.com","adventure","white","female");
        User user4= new User (4,"Marceline","Abadeer","Marcee","MarceAba@example.com","vampire","red","female");
        List<User> userList=List.of (user1,user2,user3,user4);
        when(repository.findAll ()).thenReturn (userList);
        List<User> actual = service.findAll ();
        assertEquals (userList,actual);
    }

    @Test
    void shouldFindById() {
        User user = makeUser ();
        when(repository.findById (1)).thenReturn (user);
        User actual = service.findById (1);
        assertEquals (user,actual);
        assertNull (service.findById (200));
    }

    @Test
    void shouldFindByEmail() {
        User user = makeUser ();
        when(repository.findByEmail ("johndoe@example.com")).thenReturn (user);
        User actual = service.findByEmail ("johndoe@example.com");
        assertEquals (user,actual);
        assertNull (service.findByEmail ("notanemail@what.com"));
    }

    @Test
    void shouldAdd() {
        User user = makeUser ();
        user.setUserId (0);
        user.setEmail ("iAmNewUser@gmail.com");
        User expected = makeUser ();
        expected.setUserId (5);
        expected.setEmail ("iAmNewUser@gmail.com");
        when(repository.add (user)).thenReturn (expected);
        Result<User> result = service.add (user);
        assertEquals (ResultType.SUCCESS,result.getType ());
        assertEquals (expected,result.getPayload ());
    }
    @Test
    void shouldNotAdd() {
    }

    @Test
    void shouldUpdate() {
        User user = makeUser ();
        user.setGender ("female");
        user.setEmail ("DifferentJohnDoe@gmail.com");
        user.setPassword ("ResetPassword123");
        user.setFirstName ("Jooooohn");
        user.setLastName ("Doooooooe");
        user.setFavoriteColor ("Silver");
        when(repository.update (user)).thenReturn (true);
        Result<User> actual = service.update (user);
        assertEquals (ResultType.SUCCESS,actual.getType());
    }
    @Test
    void shouldNotUpdate() {
    }

    @Test
    void shouldDeleteById() {
        when(repository.deleteById (4)).thenReturn (true);
        Result<User> result = service.deleteById (4);
        assertEquals (ResultType.SUCCESS,result.getType ());
    }
    @Test
    void shouldNotDeleteById() {
        when(repository.deleteById (20)).thenReturn (false);
        Result<User> result = service.deleteById (20);
        assertEquals (ResultType.NOT_FOUND,result.getType ());
        assertEquals (String.format("User id: %s, not found.", 20), result.getMessages().get(0));
    }
    User makeUser(){
        User user = new User ();
        user.setUserId (1);
        user.setFirstName ("John");
        user.setLastName ("Doe");
        user.setUsername ("johndoe");
        user.setEmail ("johndoe@example.com");
        user.setPassword ("password123");
        user.setFavoriteColor ("blue");
        user.setGender ("male");
        return user;
    }
}