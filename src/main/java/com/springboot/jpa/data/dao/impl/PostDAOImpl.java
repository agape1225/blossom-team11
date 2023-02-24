package com.springboot.jpa.data.dao.impl;

import com.springboot.jpa.data.dao.PostDAO;
import com.springboot.jpa.data.entity.Post;
import com.springboot.jpa.data.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class PostDAOImpl implements PostDAO {

    PostRepository postRepository;
    @Autowired
    public PostDAOImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Override
    public Post insertPost(Post post) {
        Post savedPost = postRepository.save(post);
        return savedPost;
    }

    @Override
    public Post selectPost(Long number) {
        Post selectedPost = postRepository.getById(number);
        return selectedPost;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post updatePost(Post post) throws Exception {
        Optional<Post> selectedPost = postRepository.findById(post.getNumber());
        Post updatedPost;
        if(selectedPost.isPresent()){
            //updatedPost = selectedPost.get();
            post.setUpdatedAt(LocalDateTime.now());
            post.setCreatedAt(selectedPost.get().getCreatedAt());

            updatedPost = postRepository.save(post);

        }else{
            throw new Exception();
        }

        return updatedPost;
    }

    @Override
    public void deletePost(Long number) throws Exception {
        Optional<Post> selectedPost = postRepository.findById(number);
        if(selectedPost.isPresent()){
            postRepository.delete(selectedPost.get());
        }else{
            throw new Exception();
        }
    }
}
