package com.javafullstackguru.service;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.javafullstackguru.entity.User;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    User findByUsername(String username);
	User authenticateUser(String username, String password);
    
}
