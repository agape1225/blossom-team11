package com.springboot.jpa.data.dao;

import com.springboot.jpa.data.entity.Post;

import java.util.List;

public interface PostDAO {
    Post insertPost(Post post);
    Post selectPost(Long number);
    List<Post> getAllPosts();
    Post updatePost(Post post) throws Exception;
    void deletePost(Long number) throws Exception;
}
