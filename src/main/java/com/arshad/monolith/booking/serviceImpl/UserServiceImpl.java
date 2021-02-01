package com.arshad.monolith.booking.serviceImpl;

import com.arshad.monolith.booking.beans.User;
import com.arshad.monolith.booking.beans.UserResponse;
import com.arshad.monolith.booking.mapper.UserMapper;
import com.arshad.monolith.booking.repo.UserJPARepository;
import com.arshad.monolith.booking.services.UserService;
import com.arshad.monolith.booking.utils.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserJPARepository userRepository;

    public List<UserResponse> getAllUsers() {
        List<UserResponse> userList = UserMapper.INSTANCE.mapToUserResponseModelList(userRepository.findAll());
        return userList;
    }

    public UserResponse getUserByID(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return UserMapper.INSTANCE.mapToUserResponseModel(userOptional.get());
        }
        return null;
    }

    public UserResponse addUser(User user) {
        final Optional<User> userOpt = userRepository.findByEmail(user.getEmail());
        if (userOpt.isPresent()) {
            throw new BadRequestException("email is already present in the system");
        }
        user = userRepository.save(user);
        return UserMapper.INSTANCE.mapToUserResponseModel(user);
    }

    @Override
    public UserResponse deleteUserById(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userRepository.delete(user);
            return UserMapper.INSTANCE.mapToUserResponseModel(user);
        }
        return null;
    }

}

