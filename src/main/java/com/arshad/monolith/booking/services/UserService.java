package com.arshad.monolith.booking.services;

import com.arshad.monolith.booking.beans.User;
import com.arshad.monolith.booking.beans.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();

    UserResponse getUserByID(int id);

    UserResponse addUser(User user);

    UserResponse deleteUserById(int id);
}
