package learn.platformShooter.controllers;

import learn.platformShooter.domain.Result;
import learn.platformShooter.domain.UserService;
import learn.platformShooter.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
    @PostMapping
    public ResponseEntity<Object> add(@RequestBody User user){
        Result<User> result = userService.add (user);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }
    //implement update
    @PutMapping("/{userId}")
    public ResponseEntity<Object> update(@PathVariable int userId,@RequestBody User user) {
        if (userId != user.getUserId ()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Result<User> result = userService.update (user);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }
    //implement delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleteById(@PathVariable int userId) {
        Result<User> result =userService.deleteById (userId);
        if (result.isSuccess ()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
