package com.javafullstackguru.service;

import java.util.List;
import com.javafullstackguru.entity.User;

public interface UserService {
    User saveUser(User user);  // Save user with encrypted password
    List<User> getAllUsers();  // Retrieve all users
    User getUserById(Long id);  // Retrieve user by ID
    User findByUsername(String username);  // Retrieve user by username

    User authenticateUser(String username, String password);
}
