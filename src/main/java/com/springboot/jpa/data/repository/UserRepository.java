package com.springboot.jpa.data.repository;

import com.springboot.jpa.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    <Optional>User findByEmail(String email);
}
