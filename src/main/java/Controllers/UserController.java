package Controllers;

import Entities.User;
import com.fourengineering.yourpal2.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    //@GetMapping(path="api/v1/possibleconections")
//    public List<User> getPossibleConnections(@RequestBody User user){
//
//    }

    @PostMapping("api/v1/users")
    public ResponseEntity<User> addUser(@RequestBody User user){
        try {
            User _User = new User();
            _User.setUsername(user.getUsername());
            userRepository.save(_User);
            return new ResponseEntity<>(_User, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
