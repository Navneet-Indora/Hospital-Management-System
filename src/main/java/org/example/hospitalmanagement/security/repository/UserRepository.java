package org.example.hospitalmanagement.security.repository;

import org.example.hospitalmanagement.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    //Find user by email
    Optional<User> findByEmail(String email);

    //check if email exists
    boolean existsByEmail(String email);
}
