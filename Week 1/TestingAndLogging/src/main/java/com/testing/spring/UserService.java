package com.testing.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    public User saveUser(User user) {
        if (user == null || user.getId() == null) {
            throw new IllegalArgumentException("Invalid user details");
        }
        return userRepository.save(user);
    }
}
