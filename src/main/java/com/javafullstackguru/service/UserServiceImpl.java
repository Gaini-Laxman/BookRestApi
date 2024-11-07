package com.javafullstackguru.service;

import com.javafullstackguru.entity.User;
import com.javafullstackguru.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();  // Initialize password encoder
    }

    /**
     * Save a user in the database. Encrypt the password before saving.
     */
    @Override
    public User saveUser(User user) {
        // Encrypt the user's password before saving to the database
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Retrieve all users from the database.
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieve a user by their ID.
     */
    @Override
    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        optionalUser.ifPresent(this::handleUserFound);
        return optionalUser.orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    /**
     * Retrieve a user by their username.
     */
    @Override
    public User findByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        optionalUser.ifPresent(this::handleUserFound);
        return optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    /**
     * Authenticate a user by username and password.
     * Compare provided password with the stored (encoded) password.
     */
    @Override
    public User authenticateUser(String username, String password) {
        User user = findByUsername(username);
        
        if (passwordEncoder.matches(password, user.getPassword())) {
            return user; // Authentication successful
        } else {
            return null; // Authentication failed
        }
    }

    private void handleUserFound(User user) {
        System.out.println("User found: " + user.getUsername());
    }
}
