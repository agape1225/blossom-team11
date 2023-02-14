package com.springboot.jpa.controller;

import com.springboot.jpa.data.dto.PostDto;
import com.springboot.jpa.data.dto.PostResponseDto;
import com.springboot.jpa.data.entity.Post;
import com.springboot.jpa.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping()
    public ResponseEntity<Post> getPost(Long number){
        Post post = postService.getPost(number);
        return ResponseEntity.status(HttpStatus.OK).body(post);

    }

    @GetMapping("/getAllPost")
    public ResponseEntity<List<Post>> getAllPost(){
        List<Post> selectedPost = postService.getAllPost();
        return ResponseEntity.status(HttpStatus.OK).body(selectedPost);
    }

    @PostMapping()
    public ResponseEntity<PostResponseDto> createPost(
            @RequestBody PostDto post){
        System.out.println(post);
        PostResponseDto savedPost = postService.savePost(post);
        System.out.println("성공 했을까? = " + savedPost);
        return ResponseEntity.status(HttpStatus.OK).body(savedPost);
    }

    @DeleteMapping()
    public ResponseEntity<String> deletePost(Long number) throws Exception{
        postService.deletePost(number);
        return ResponseEntity.status(HttpStatus.OK).body("성공적으로 삭제 되었습니다.");
    }
}
