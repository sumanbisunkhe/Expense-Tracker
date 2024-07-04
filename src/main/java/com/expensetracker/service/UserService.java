package com.expensetracker.service;

import com.expensetracker.dto.UserDto;
import com.expensetracker.model.User;

import java.util.List;

public interface UserService {
    boolean registerUser(UserDto userDto);
    User updateUser(Long userId, UserDto userDto);
    User getUserById(Long userId);
    User getUserByUsername(String username);
    List<User> getAllUsers();
    String deleteUserById(Long userId);
}
