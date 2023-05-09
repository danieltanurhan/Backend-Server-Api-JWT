package com.socialnetwork.networkplatform.repository;

import com.socialnetwork.networkplatform.model.Post;
import com.socialnetwork.networkplatform.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findPostByUser(@Param("user")User user);

    List<Post> findAllByOrderByIdDesc();
    
}