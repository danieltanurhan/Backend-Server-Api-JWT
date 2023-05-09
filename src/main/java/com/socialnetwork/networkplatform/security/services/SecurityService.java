package com.socialnetwork.networkplatform.security.services;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    public MyUserDetails getUser() {
        MyUserDetails userDetailsPrincipal = null;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Object principal = securityContext.getAuthentication().getPrincipal();
        if(principal instanceof MyUserDetails) {
            userDetailsPrincipal = ((MyUserDetails) principal);
        } 

        return userDetailsPrincipal;
    }
    
}
