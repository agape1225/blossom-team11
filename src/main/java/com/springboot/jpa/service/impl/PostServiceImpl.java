package com.springboot.jpa.service.impl;

import com.springboot.jpa.data.dao.PostDAO;
import com.springboot.jpa.data.dao.UserDAO;
import com.springboot.jpa.data.dto.PostDto;
import com.springboot.jpa.data.dto.PostResponseDto;
import com.springboot.jpa.data.dto.UserDto;
import com.springboot.jpa.data.entity.Post;
import com.springboot.jpa.data.entity.User;
import com.springboot.jpa.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Post getPost(Long number) {

        return postDAO.selectPost(number);
    }

    @Override
    public List<Post> getAllPost() {
        return postDAO.getAllPosts();
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
        responsePost.setUser(userDto);

        return responsePost;
    }

    @Override
    public Post changePost(Post post) throws Exception {
        Post updatedPost = postDAO.updatePost(post);
        return updatedPost;
    }

    @Override
    public void deletePost(Long number) throws Exception {
        postDAO.deletePost(number);
    }
}
