package com.fourengineering.yourpal.Controllers;

import com.fourengineering.yourpal.Entities.User;
import com.fourengineering.yourpal.Misc.BadHabits;
import com.fourengineering.yourpal.Misc.Hobbies;
import com.fourengineering.yourpal.Repositories.UserRepository;
import com.fourengineering.yourpal.Services.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("api/v1/userReg")
    public ResponseEntity<User> addUser(@RequestBody Map<String, String> request) {
        try {
            User _User = new User();
            _User.setUsername(request.get("username"));
            _User.setName(request.get("name"));
            _User.setSurname(request.get("surname"));
            _User.setPassword(PasswordEncoder.encodePassword(request.get("password")));
            _User.setBio(request.get("Bio"));
            _User.setEmail(request.get("email"));
            _User.setMentor(Boolean.valueOf(request.get("mentor")));
            _User.setHobbies(Hobbies.valueOf(request.get("hobbies")));
            _User.setBadHabits(BadHabits.valueOf(request.get("badHabits")));
            _User.setConnections("");
            _User.setRating(1.0);
            userRepository.save(_User);
            return new ResponseEntity<>(_User, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        catch(Exception e){
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
    @RequestMapping(value = "api/v1/login", method = RequestMethod.GET)
    public ResponseEntity<User> HttpStatus(@RequestBody Map<String, String> request){
        try {
            User user = userRepository.findByEmail(request.get("email"));
            boolean result = PasswordEncoder.validatePassword(user.getPassword(), request.get("password"));
            if (result)
                return new ResponseEntity<>(user, HttpStatus.OK);
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        catch(NullPointerException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "api/v1/connect", method = RequestMethod.PUT)
    public ResponseEntity<User> connect(@RequestBody Map<String, String> request, @RequestParam String username){
        try{
            User user = userRepository.findByEmail(request.get("email"));
            user.setConnections(user.getConnections() + ", " + username);
            userRepository.save(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        catch (NullPointerException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "api/v1/rate", method = RequestMethod.PUT)
    public ResponseEntity<User> rate(@RequestBody Map<String,String> rating, @RequestParam String email) {
        try{
            User user = userRepository.findByEmail(email);
            if(!User.validateRating(Double.parseDouble(rating.get("rating"))))
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            user.setRating((Double.parseDouble(rating.get("rating")) + user.getRating())/2);
            userRepository.save(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        catch (NullPointerException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
   // @RequestMapping(value = "api/v1/rate")


}