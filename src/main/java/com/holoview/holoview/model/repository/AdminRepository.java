package com.holoview.holoview.model.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.holoview.holoview.model.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, UUID> {
    Optional<Admin> findByEmail(String email);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    @Query("SELECT a FROM Admin a WHERE email = ?1 OR username = ?1")
    Optional<Admin> findByEmailOrUsername(String login);
}