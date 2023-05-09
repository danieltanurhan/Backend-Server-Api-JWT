package com.socialnetwork.networkplatform.payload.request;

import lombok.Data;

@Data
public class UserLogin {
    private String username;
    private String password;
}
