package com.springboot.jpa.data.repository;

import com.springboot.jpa.data.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
