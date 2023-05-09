package com.socialnetwork.networkplatform.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.socialnetwork.networkplatform.model.User;
import com.socialnetwork.networkplatform.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) 
            throws UsernameNotFoundException{
                User user = userRepository.getUserByUsername(username);
                if (user == null) {
                    throw new UsernameNotFoundException("could not find user");
                }
                return MyUserDetails.build(user);
            }
    
    
}
