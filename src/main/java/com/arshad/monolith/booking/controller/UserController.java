package com.arshad.monolith.booking.controller;

import com.arshad.monolith.booking.beans.User;
import com.arshad.monolith.booking.beans.UserResponse;
import com.arshad.monolith.booking.services.UserService;
import com.arshad.monolith.booking.utils.Constants;
import com.arshad.monolith.booking.utils.exceptions.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {


    @Autowired
    private UserService userServiceImpl;

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userServiceImpl.getAllUsers();
    }

    @GetMapping(path = "/{id}")
    public UserResponse getUser(@PathVariable int id) {
        UserResponse user = userServiceImpl.getUserByID(id);
        if (user == null) {
            throw new RecordNotFoundException(String.format(Constants.CANNOT_FIND_RECORD, "user", id));
        }
        return user;
    }

    @PostMapping
    public UserResponse addUser(@RequestBody User user) {
        final UserResponse userResponse = userServiceImpl.addUser(user);
        return userResponse;
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        UserResponse user = userServiceImpl.deleteUserById(id);
        if (user == null) {
            throw new RecordNotFoundException(String.format(Constants.CANNOT_DELETE_RECORD, "user", id));
        }
        return ResponseEntity.noContent().build();
    }


}
