package com.expensetracker.controller;

import com.expensetracker.dto.UserDto;
import com.expensetracker.model.User;
import com.expensetracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserDto userDto) {
        boolean isRegistered = userService.registerUser(userDto);
        if (isRegistered) {
            return ResponseEntity.ok(Collections.singletonMap("message", "User registered successfully."));
        } else {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "User registration failed. Please try again."));
        }
    }



    @PutMapping("/update/id/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        User updatedUser = userService.updateUser(userId, userDto);
        String message = "User '" + updatedUser.getFullName() + "' updated successfully.";

        // Create a map to hold both the message and the updated user details
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("user", updatedUser); // Add the updated user object

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/id/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok().body(userResponse(user));
    }

    @GetMapping("/username/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        return ResponseEntity.ok().body(userResponse(user));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<Map<String, Object>> usersResponse = users.stream()
                .map(this::userResponses)
                .collect(Collectors.toList());

        String message = "User list fetched successfully";
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("users", usersResponse);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/delete/id/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long userId) {
        String deletionMessage = userService.deleteUserById(userId);
        return ResponseEntity.ok().body(Collections.singletonMap("message", deletionMessage));
    }

    private Map<String, Object> userResponses(User user) {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("id", user.getId());
        userMap.put("username", user.getUsername());
        userMap.put("email", user.getEmail());
        userMap.put("fullName", user.getFullName());
        userMap.put("address", user.getAddress());
        userMap.put("age", user.getAge());
        userMap.put("gender", user.getGender());
        // Add other necessary user details

        return userMap;
    }

    private Map<String, Object> userResponse(User user) {
        String message = "User '" + user.getFullName() + "' retrieved successfully.";
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", message);
        response.put("user", user);
        return response;
    }
}
