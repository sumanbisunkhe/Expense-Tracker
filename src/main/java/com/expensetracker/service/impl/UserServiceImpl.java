package com.expensetracker.service.impl;

import com.expensetracker.dto.UserDto;
import com.expensetracker.enums.Role;
import com.expensetracker.model.User;
import com.expensetracker.repo.UserRepository;
import com.expensetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean registerUser(UserDto userDto) {
        try {
            User newUser = new User();
            newUser.setUsername(userDto.getUsername());
            newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
            newUser.setEmail(userDto.getEmail());
            newUser.setFullName(userDto.getFullName());
            newUser.setAddress(userDto.getAddress());
            newUser.setAge(userDto.getAge());
            newUser.setGender(userDto.getGender());

            // Assign role(s)
            Set<Role> roles = new HashSet<>();
            roles.add(Role.USER);  // Default role for new users
            newUser.setRoles(roles);

            userRepository.save(newUser);
            return true; // Registration successful
        } catch (Exception e) {
            return false; // Registration failed
        }
    }


    @Override
    public User updateUser(Long userId, UserDto userDto) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setUsername(userDto.getUsername());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setFullName(userDto.getFullName());
        existingUser.setAddress(userDto.getAddress());
        existingUser.setAge(userDto.getAge());
        existingUser.setGender(userDto.getGender());

        userRepository.save(existingUser);

        return existingUser;
    }


    @Override
    public User getUserById(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new RuntimeException("User with id " + userId + " not found");
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User with username '" + username + "' not found");
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public String deleteUserById(Long userId) {
        User userToDelete = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(userToDelete);
        return "User '" + userToDelete.getFullName() + "' deleted successfully.";
    }
}