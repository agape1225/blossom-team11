package com.springboot.jpa.service;

import com.springboot.jpa.data.dto.PostDto;
import com.springboot.jpa.data.dto.PostResponseDto;
import com.springboot.jpa.data.entity.Post;

import java.util.List;

public interface PostService {

    PostResponseDto getPost(Long number);
    List<PostResponseDto> getAllPost();
    List<PostResponseDto> getPostsByUser(String email);
    PostResponseDto savePost(PostDto post);
    PostResponseDto changePost(Long number, PostDto post) throws Exception;
    void deletePost(Long number) throws Exception;

}
