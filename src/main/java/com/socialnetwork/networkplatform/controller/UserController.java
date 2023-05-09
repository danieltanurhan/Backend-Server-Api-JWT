package com.socialnetwork.networkplatform.controller;

import com.socialnetwork.networkplatform.payload.request.UserLogin;
import com.socialnetwork.networkplatform.payload.request.UserRegistration;
import com.socialnetwork.networkplatform.payload.response.MessageResponse;
import com.socialnetwork.networkplatform.payload.response.UserInfoResponse;
import com.socialnetwork.networkplatform.security.jwt.JwtUtils;
import com.socialnetwork.networkplatform.security.services.MyUserDetails;
import com.socialnetwork.networkplatform.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistration payload) {
      
      if(userService.checkUsername(payload.getUsername())){
        return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is alreadty taken!"));
      }
      
      if(userService.checkEmail(payload.getEmail())){
        return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
      }

      userService.registerUser(payload);

      return ResponseEntity.ok().body("User registered successfully!");
    } 

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody UserLogin payload) {
      Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(payload.getUsername(), payload.getPassword()));
      
      SecurityContextHolder.getContext().setAuthentication(authentication);

      MyUserDetails myUser = (MyUserDetails) authentication.getPrincipal();

      ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(myUser);

      List<String> roles = myUser.getAuthorities().stream()
        .map(item->item.getAuthority())
        .collect(Collectors.toList());
    
      return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
      .body(new UserInfoResponse(myUser.getId(),
                                myUser.getUsername(),
                                myUser.getEmail(),
                                roles));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
      ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
      return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
          .body(new MessageResponse("You've been signed out!"));
    }
}
