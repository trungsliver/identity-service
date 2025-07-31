package com.ductrungsl.identity_service.repository;

import com.ductrungsl.identity_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    // check trùng username khi create
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
