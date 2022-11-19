package com.fourengineering.yourpal.Controllers;

import com.fourengineering.yourpal.Entities.User;
import com.fourengineering.yourpal.Misc.Hobbies;
import com.fourengineering.yourpal.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("api/v1/user")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        try {
            User _User = new User();
            _User.setUsername(user.getUsername());
            _User.setBio(user.getBio());
            _User.setEmail(user.getEmail());
            _User.setMentor(user.isMentor());
            _User.setHobbies(user.getHobbies());
            _User.setBadHabits(user.getBadHabits());
            userRepository.save(_User);
            return new ResponseEntity<>(_User, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("api/v1/users")
    public ResponseEntity<List<User>> getUsers(@RequestBody User user) {
        try {
            List<User> users = user.getPossibleConnections(userRepository);
            return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }
    }
}