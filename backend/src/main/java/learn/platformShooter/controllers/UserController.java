package learn.platformShooter.controllers;

import learn.platformShooter.domain.UserService;
import learn.platformShooter.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){this.userService=userService;}
    @GetMapping
    public List<User> findAll(){
        return userService.findAll ();
    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> findById(@PathVariable int userId){
        User user = userService.findById (userId);
        if(user==null){
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok (user);
    }
    @GetMapping("/{email}")
    public ResponseEntity<User> findByEmail(@PathVariable String email){
        User user = userService.findByEmail (email);
        if(user==null){
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok (user);
    }
    //implement create
    //implement update
    //implement delete

}
