package com.socialnetwork.networkplatform.payload.request;

import java.util.Set;

import lombok.Data;

@Data
public class UserRegistration {

    private String username;
    private String password;  
    private String email;
    private Set<String> role;    
}
