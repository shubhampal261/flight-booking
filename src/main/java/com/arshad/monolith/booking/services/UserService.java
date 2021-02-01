package com.arshad.monolith.booking.services;

import com.arshad.monolith.booking.beans.User;
import com.arshad.monolith.booking.beans.UserResponse;

import java.util.List;

public interface UserService {

    public List<UserResponse> getAllUsers();

    public UserResponse getUserByID(int id);

    public UserResponse addUser(User user);

    public UserResponse deleteUserById(int id);
}
