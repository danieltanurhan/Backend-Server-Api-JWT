package com.socialnetwork.networkplatform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialnetwork.networkplatform.model.ERole;
import com.socialnetwork.networkplatform.model.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
}
