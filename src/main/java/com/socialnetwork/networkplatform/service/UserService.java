package com.socialnetwork.networkplatform.service;


import com.socialnetwork.networkplatform.model.ERole;
import com.socialnetwork.networkplatform.model.Role;
import com.socialnetwork.networkplatform.model.User;
import com.socialnetwork.networkplatform.payload.request.UserRegistration;
import com.socialnetwork.networkplatform.repository.RoleRepository;
import com.socialnetwork.networkplatform.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User registerUser(UserRegistration payload) {
        User user = new User();
        user.setUsername(payload.getUsername());
        user.setEmail(payload.getEmail());
        String encodedPassword = new BCryptPasswordEncoder().encode(payload.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        
        Set<String> stringRoles = payload.getRole();
        Set<Role> roles = new HashSet<>();
        if(stringRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            stringRoles.forEach(role -> {
                switch(role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    public User getUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }

    public String getEmailByUsername(String username) {
        return userRepository.getUserByUsername(username).getEmail();
    }

    public Boolean checkUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public Boolean checkEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }
}
