package com.socialnetwork.networkplatform.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialnetwork.networkplatform.model.Post;
import com.socialnetwork.networkplatform.model.User;
import com.socialnetwork.networkplatform.security.services.MyUserDetails;
import com.socialnetwork.networkplatform.security.services.SecurityService;
import com.socialnetwork.networkplatform.service.PostService;
import com.socialnetwork.networkplatform.service.UserService;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private UserService userService;

    @PostMapping("/private/addpost")
    public ResponseEntity<?> addPost(@RequestBody String content) throws NullPointerException {
        MyUserDetails user = securityService.getUser();
        Post savedPost = postService.savePost(user, content);
        return ResponseEntity.created(URI.create("/private/mypost")).body(savedPost);
    }

    @PostMapping("/private/myposts")
    public ResponseEntity<?> myPosts() throws NullPointerException {
        User user = userService.getUserByEmail(securityService.getUser().getEmail());
        List<Post> postList = postService.getPostsOfUser(user.getId());
        return ResponseEntity.ok(postList);
        
    }
    //WHEN EXPOSING DATA HERE IS IT GOOD TO GIVE THE CLASS DIRECTLY OR USE DTOS
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts(){
        List<Post> postList = postService.getAllPosts();
        return ResponseEntity.ok(postList);
    }

    @GetMapping("/{userId}/posts")
    public ResponseEntity<?> getPostOfUser(@PathVariable Long userId){
        List<Post> postList = postService.getPostsOfUser(userId);
        return ResponseEntity.ok(postList);
    }

    
}
