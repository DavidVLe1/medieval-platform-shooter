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
        // can't add null
        Result<User> result = service.add (null);
        assertEquals (ResultType.INVALID,result.getType ());
        //should not add when userId is not 0.
        User user = makeUser ();
        user.setEmail ("123123@gmail.com");
        user.setUserId (2);
        result=service.add (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("userId cannot be set for `add` operation.",result.getMessages ().get (0));
        //should not add duplicate email.
        user=makeUser ();
        user.setUserId (0);
        user.setEmail ("johndoe@example.com");
        when(repository.findByEmail ("johndoe@example.com")).thenReturn (makeUser ());
        result=service.add (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("user email is already in the system.",result.getMessages ().get (0));
        //should not add empty/null first name.
        user=makeUser ();
        user.setUserId (0);
        user.setEmail ("differentemail@gmail.com");
        user.setFirstName ("");
        result=service.add (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("user firstName is required.",result.getMessages ().get (0));

        user=makeUser ();
        user.setUserId (0);
        user.setEmail ("differentemail@gmail.com");
        user.setFirstName (null);
        result=service.add (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("user firstName is required.",result.getMessages ().get (0));

        //should not add empty/null last name.
        user=makeUser ();
        user.setUserId (0);
        user.setEmail ("differentemail@gmail.com");
        user.setLastName ("");
        result=service.add (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("user lastName is required.",result.getMessages ().get (0));

        user=makeUser ();
        user.setUserId (0);
        user.setEmail ("differentemail@gmail.com");
        user.setLastName (null);
        result=service.add (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("user lastName is required.",result.getMessages ().get (0));

        //should not add empty/null username.
        user=makeUser ();
        user.setUserId (0);
        user.setEmail ("differentemail@gmail.com");
        user.setUsername ("");
        result=service.add (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("user username is required.",result.getMessages ().get (0));

        user=makeUser ();
        user.setUserId (0);
        user.setEmail ("differentemail@gmail.com");
        user.setUsername (null);
        result=service.add (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("user username is required.",result.getMessages ().get (0));

        //should not add empty/null email.
        user=makeUser ();
        user.setUserId (0);
        user.setEmail ("");
        result=service.add (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("user email is required.",result.getMessages ().get (0));

        user=makeUser ();
        user.setUserId (0);
        user.setEmail (null);
        result=service.add (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("user email is required.",result.getMessages ().get (0));

        //should not add empty/null password.
        user=makeUser ();
        user.setUserId (0);
        user.setEmail ("differentemail@gmail.com");
        user.setPassword ("");
        result=service.add (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("user password is required.",result.getMessages ().get (0));

        user=makeUser ();
        user.setUserId (0);
        user.setEmail ("differentemail@gmail.com");
        user.setPassword (null);
        result=service.add (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("user password is required.",result.getMessages ().get (0));

        //should not add empty/null favorite color.
        user=makeUser ();
        user.setUserId (0);
        user.setEmail ("differentemail@gmail.com");
        user.setFavoriteColor ("");
        result=service.add (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("user favoriteColor is required.",result.getMessages ().get (0));

        user=makeUser ();
        user.setUserId (0);
        user.setEmail ("differentemail@gmail.com");
        user.setFavoriteColor (null);
        result=service.add (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("user favoriteColor is required.",result.getMessages ().get (0));

        //should not add empty/null gender.
        user=makeUser ();
        user.setUserId (0);
        user.setEmail ("differentemail@gmail.com");
        user.setGender ("");
        result=service.add (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("user gender is required.",result.getMessages ().get (0));

        user=makeUser ();
        user.setUserId (0);
        user.setEmail ("differentemail@gmail.com");
        user.setGender (null);
        result=service.add (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("user gender is required.",result.getMessages ().get (0));
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
        // can't update without set userId.
        Result<User> result = service.update (null);
        assertEquals (ResultType.INVALID, result.getType ());
        //should not update when userId is 0.
        User user = makeUser ();
        user.setEmail ("123123@gmail.com");
        user.setUserId (0);
        result=service.update (user);
        assertEquals (ResultType.INVALID,result.getType ());
        //should not update duplicate email.
        user=makeUser ();
        user.setEmail ("johndoe@example.com");
        when(repository.findByEmail ("johndoe@example.com")).thenReturn (makeUser ());
        result=service.update (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("user email is already in the system.",result.getMessages ().get (0));

        //should not update empty/null first name.
        user=makeUser ();
        user.setUserId (0);
        user.setEmail ("differentemail@gmail.com");
        user.setFirstName ("");
        result=service.update (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("user firstName is required.",result.getMessages ().get (0));

        user=makeUser ();
        user.setUserId (0);
        user.setEmail ("differentemail@gmail.com");
        user.setFirstName (null);
        result=service.update (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("user firstName is required.",result.getMessages ().get (0));

        //should not addupdate empty/null last name.
        user=makeUser ();
        user.setUserId (0);
        user.setEmail ("differentemail@gmail.com");
        user.setLastName ("");
        result=service.update (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("user lastName is required.",result.getMessages ().get (0));

        user=makeUser ();
        user.setUserId (0);
        user.setEmail ("differentemail@gmail.com");
        user.setLastName (null);
        result=service.update (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("user lastName is required.",result.getMessages ().get (0));

        //should not update empty/null username.
        user=makeUser ();
        user.setUserId (0);
        user.setEmail ("differentemail@gmail.com");
        user.setUsername ("");
        result=service.update (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("user username is required.",result.getMessages ().get (0));

        user=makeUser ();
        user.setUserId (0);
        user.setEmail ("differentemail@gmail.com");
        user.setUsername (null);
        result=service.update (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("user username is required.",result.getMessages ().get (0));

        //should not update empty/null email.
        user=makeUser ();
        user.setUserId (0);
        user.setEmail ("");
        result=service.update (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("user email is required.",result.getMessages ().get (0));

        user=makeUser ();
        user.setUserId (0);
        user.setEmail (null);
        result=service.update (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("user email is required.",result.getMessages ().get (0));

        //should not update empty/null password.
        user=makeUser ();
        user.setUserId (0);
        user.setEmail ("differentemail@gmail.com");
        user.setPassword ("");
        result=service.update (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("user password is required.",result.getMessages ().get (0));

        user=makeUser ();
        user.setUserId (0);
        user.setEmail ("differentemail@gmail.com");
        user.setPassword (null);
        result=service.update (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("user password is required.",result.getMessages ().get (0));

        //should not update empty/null favorite color.
        user=makeUser ();
        user.setUserId (0);
        user.setEmail ("differentemail@gmail.com");
        user.setFavoriteColor ("");
        result=service.update (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("user favoriteColor is required.",result.getMessages ().get (0));

        user=makeUser ();
        user.setUserId (0);
        user.setEmail ("differentemail@gmail.com");
        user.setFavoriteColor (null);
        result=service.update (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("user favoriteColor is required.",result.getMessages ().get (0));

        //should not update empty/null gender.
        user=makeUser ();
        user.setUserId (0);
        user.setEmail ("differentemail@gmail.com");
        user.setGender ("");
        result=service.update (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("user gender is required.",result.getMessages ().get (0));

        user=makeUser ();
        user.setUserId (0);
        user.setEmail ("differentemail@gmail.com");
        user.setGender (null);
        result=service.update (user);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("user gender is required.",result.getMessages ().get (0));

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