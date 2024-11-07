package com.javafullstackguru.controller;

import com.javafullstackguru.entity.User;
import com.javafullstackguru.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Signup endpoint: encrypt the password before saving
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        try {
            User savedUser = userService.saveUser(user);
            return ResponseEntity.ok(savedUser); // Return saved user, excluding password for security
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error signing up: " + e.getMessage());
        }
    }

    // Get all users
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Login endpoint: validate the user's credentials and return a response
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        User user = userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
        if (user != null) {
            // Successful login: you could return a JWT token here
            return ResponseEntity.ok("Login successful"); // Or return token instead
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}
