package com.socialnetwork.networkplatform;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.socialnetwork.networkplatform.model.User;
import com.socialnetwork.networkplatform.repository.UserRepository;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        userRepository.save(user);
        User savedUser = userRepository.getUserByUsername("testuser");
        assertNotNull(savedUser);
        assertEquals("testuser", savedUser.getUsername());
        assertEquals("password", savedUser.getPassword());
        userRepository.delete(user);
    }
}
