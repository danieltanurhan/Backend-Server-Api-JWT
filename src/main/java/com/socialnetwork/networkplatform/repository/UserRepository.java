package com.socialnetwork.networkplatform.repository;

import com.socialnetwork.networkplatform.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByUsername(@Param("username")String username);

    User getUserByEmail(@Param("email")String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    
}