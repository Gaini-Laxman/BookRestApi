package com.javafullstackguru.service;

import com.javafullstackguru.entity.User;
import com.javafullstackguru.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // Constructor injection for UserRepository
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Save a user in the database.
     *
     * @param user The user object to be saved.
     * @return The saved user object.
     */
    @Override
    public User saveUser(User user) {
        return userRepository.save(user); // Save the user to the database
    }

    /**
     * Retrieve all users from the database.
     *
     * @return A list of all users.
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll(); // Fetch all users from the repository
    }

    /**
     * Retrieve a user by their ID.
     *
     * @param id The ID of the user.
     * @return The user object if found.
     */
    @Override
    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id); // Look up the user by ID
        return optionalUser.orElseThrow(() -> new RuntimeException("User not found with id: " + id)); // Throw exception if not found
    }

    /**
     * Retrieve a user by their username.
     *
     * @param username The username of the user.
     * @return The user object if found.
     */
    @Override
    public User findByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username); // Find user by username
        return optionalUser.orElseThrow(() -> new RuntimeException("User not found with username: " + username)); // Throw exception if not found
    }

    @Override
    public User authenticateUser(String username, String password) {
        return null;
    }
}
