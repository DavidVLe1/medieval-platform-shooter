package learn.platformShooter.domain;

import learn.platformShooter.data.UserRepository;
import learn.platformShooter.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;
    public UserService(UserRepository repository){this.repository=repository;}

    public List<User> findAll(){return repository.findAll ();}
    public User findById(int userId){return repository.findById (userId);}

    public User findByEmail(String email){return repository.findByEmail (email);}

    public Result<User> add(User user){
        Result<User> result = validate (user);

        if (!result.isSuccess()) {
            return result;
        }

        if (user.getUserId () != 0) {
            result.addMessage("userId cannot be set for `add` operation.", ResultType.INVALID);
            return result;
        }
        user = repository.add(user);
        result.setPayload(user);
        return result;
    }
    public Result<User> update(User user){
        Result<User> result = validate (user);

        if (!result.isSuccess()) {
            return result;
        }

        if (user.getUserId () <= 0) {
            result.addMessage("userId must be set for `update` operation.", ResultType.INVALID);
            return result;
        }
        if (!repository.update(user)) {
            String msg = String.format("userId: %s, not found", user.getUserId ());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    }
    public Result<User> deleteById(int userId){
        Result<User> result = new Result<> ();
        if (!repository.deleteById(userId)) {
            String msg = String.format("User id: %s, not found.", userId);
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    }

    public Result<User> validate(User user){
        Result<User> result = new Result<> ();
        if (user == null) {
            result.addMessage("user cannot be null.", ResultType.INVALID);
            return result;
        }
        if (repository.findByEmail(user.getEmail()) != null) {
            result.addMessage("user email is already in the system.", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(user.getFirstName ())) {
            result.addMessage("user firstName is required.", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(user.getLastName ())) {
            result.addMessage("user lastName is required.", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(user.getUsername ())) {
            result.addMessage("user username is required.", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(user.getEmail ())) {
            result.addMessage("user email is required.", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(user.getPassword ())) {
            result.addMessage("user password is required.", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(user.getFavoriteColor ())) {
            result.addMessage("user favoriteColor is required.", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(user.getGender ())) {
            result.addMessage("user gender is required.", ResultType.INVALID);
        }

        return result;
    }

}
