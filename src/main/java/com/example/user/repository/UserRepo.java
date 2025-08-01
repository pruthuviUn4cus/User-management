package com.example.user.repository;

import com.example.user.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity,Integer> {
    Optional<UserEntity> findByUsername(String userName);

    Optional<UserEntity> findByEmail(String email);

    Page<UserEntity> findByDeletedFalse(Pageable pageable);
}
