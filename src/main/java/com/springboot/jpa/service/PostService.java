package com.springboot.jpa.service;

import com.springboot.jpa.data.dto.PostDto;
import com.springboot.jpa.data.dto.PostResponseDto;
import com.springboot.jpa.data.entity.Post;

import java.util.List;

public interface PostService {

    Post getPost(Long number);
    List<Post> getAllPost();
    PostResponseDto savePost(PostDto post);
    Post changePost(Post post) throws Exception;
    void deletePost(Long number) throws Exception;

}
