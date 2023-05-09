package com.socialnetwork.networkplatform.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialnetwork.networkplatform.dtos.PostDetails;
import com.socialnetwork.networkplatform.model.Post;
import com.socialnetwork.networkplatform.model.User;
import com.socialnetwork.networkplatform.repository.PostRepository;
import com.socialnetwork.networkplatform.repository.UserRepository;
import com.socialnetwork.networkplatform.security.services.MyUserDetails;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    public Post savePost(MyUserDetails userDto, String content) {
        Post post = new Post();
        User user = userRepository.getUserByUsername(userDto.getUsername());
        post.setUser(user);
        post.setContent(content);    
        return postRepository.save(post);
    }

    public List<Post> getPostsOfUser(Long userId) {
        List<Post> postList = postRepository.findPostByUser(userRepository.getReferenceById(userId));
        List<PostDetails> postDetailsList = new ArrayList<>();
        for(Post post :postList) {
            postDetailsList.add(new PostDetails(post.getId(), post.getContent(), post.getCreatedDate()));
        }
        //Check back to see if using postDetails is important here
        return postList;
    }

    public List<Post> getAllPosts(){
        return postRepository.findAllByOrderByIdDesc();
    }
}
