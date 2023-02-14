package com.springboot.jpa.service.impl;

import com.springboot.jpa.data.dao.PostDAO;
import com.springboot.jpa.data.dao.UserDAO;
import com.springboot.jpa.data.dto.PostDto;
import com.springboot.jpa.data.dto.PostResponseDto;
import com.springboot.jpa.data.dto.UserDto;
import com.springboot.jpa.data.entity.Post;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.data.entity.User;
import com.springboot.jpa.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    PostDAO postDAO;
    UserDAO userDAO;

    @Autowired
    public PostServiceImpl(PostDAO postDAO, UserDAO userDAO){
        this.postDAO = postDAO;
        this.userDAO = userDAO;
    }

    @Override
    public PostResponseDto getPost(Long number) {

        Post selectedPost = postDAO.selectPost(number);
        PostResponseDto postResponseDto = new PostResponseDto();
        UserDto userDto = new UserDto();

        userDto.setAge(selectedPost.getUser().getAge());
        userDto.setName(selectedPost.getUser().getName());
        userDto.setEmail(selectedPost.getUser().getEmail());
        userDto.setKakaoId(selectedPost.getUser().getKakaoId());

        postResponseDto.setUser(userDto);
        postResponseDto.setTitle(selectedPost.getTitle());
        postResponseDto.setContent(selectedPost.getContent());
        postResponseDto.setNumber(selectedPost.getNumber());
        postResponseDto.setUpdatedDate(selectedPost.getUpdatedAt());
        postResponseDto.setCreatedDate(selectedPost.getCreatedAt());

        return postResponseDto;
    }

    @Override
    public List<PostResponseDto> getAllPost() {
        List<Post> postList = postDAO.getAllPosts();
        List<PostResponseDto> postResponseDto = new LinkedList<>();

        for(Post post : postList){
            PostResponseDto buff = new PostResponseDto();
            UserDto userDto = new UserDto();

            userDto.setAge(post.getUser().getAge());
            userDto.setName(post.getUser().getName());
            userDto.setEmail(post.getUser().getEmail());
            userDto.setKakaoId(post.getUser().getKakaoId());

            buff.setUser(userDto);
            buff.setTitle(post.getTitle());
            buff.setContent(post.getContent());
            buff.setNumber(post.getNumber());
            buff.setUpdatedDate(post.getUpdatedAt());
            buff.setCreatedDate(post.getCreatedAt());

            postResponseDto.add(buff);
        }

        return postResponseDto;
    }

    @Override
    public PostResponseDto savePost(PostDto post) {
        User user = userDAO.selectUser(post.getEmail());
        Post newPost = new Post();
        newPost.setUser(user);
        newPost.setTitle(post.getTitle());
        newPost.setContent(post.getContent());

        Post savedPost = postDAO.insertPost(newPost);
        PostResponseDto responsePost = new PostResponseDto();
        UserDto userDto = new UserDto();

        userDto.setAge(savedPost.getUser().getAge());
        userDto.setEmail(savedPost.getUser().getEmail());
        userDto.setName(savedPost.getUser().getName());
        userDto.setKakaoId(savedPost.getUser().getKakaoId());

        responsePost.setContent(savedPost.getContent());
        responsePost.setTitle(savedPost.getTitle());
        responsePost.setNumber(savedPost.getNumber());
        responsePost.setUser(userDto);
        responsePost.setCreatedDate(savedPost.getCreatedAt());
        responsePost.setUpdatedDate(savedPost.getUpdatedAt());

        return responsePost;
    }

    @Override
    public PostResponseDto changePost(Long number, PostDto post) throws Exception {
        Post newPost = postDAO.selectPost(number);
        newPost.setContent(post.getContent());
        newPost.setTitle(post.getTitle());

        Post updatedPost = postDAO.updatePost(newPost);

        PostResponseDto postResponseDto = new PostResponseDto();
        UserDto updatedUser = new UserDto();

        updatedUser.setKakaoId(updatedPost.getUser().getKakaoId());
        updatedUser.setName(updatedPost.getUser().getName());
        updatedUser.setEmail(updatedPost.getUser().getEmail());
        updatedUser.setAge(updatedPost.getUser().getAge());

        postResponseDto.setUser(updatedUser);
        postResponseDto.setContent(updatedPost.getContent());
        postResponseDto.setTitle(updatedPost.getTitle());
        postResponseDto.setNumber(updatedPost.getNumber());
        postResponseDto.setUpdatedDate(updatedPost.getUpdatedAt());
        postResponseDto.setCreatedDate(updatedPost.getUpdatedAt());

        return postResponseDto;
    }

    @Override
    public void deletePost(Long number) throws Exception {
        postDAO.deletePost(number);
    }
}
