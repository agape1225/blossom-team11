package com.springboot.jpa.data.repository;

import com.springboot.jpa.data.entity.Post;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.data.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Test
    void relationshipTest1(){

        User user = new User();
        user.setName("성창규");
        user.setKakaoId("scg9268");
        user.setAge("25");
        user.setEmail("scg9268@kangnam.ac.kr");

        userRepository.save(user);

        Post post1 = new Post();
        post1.setContent("나랑 술 먹을 사람 구함~");
        post1.setTitle("21일에 술 먹을 사람 구합니다.");
        post1.setUsing_data(true);
        post1.setUser(user);

        postRepository.save(post1);

        Post post2 = new Post();
        post2.setContent("나랑 술 먹을 사람 구함~22");
        post2.setTitle("21일에 술 먹을 사람 구합니다.22");
        post2.setUsing_data(true);
        post2.setUser(user);

        postRepository.save(post2);

        Post post3 = new Post();
        post3.setContent("나랑 술 먹을 사람 구함~33");
        post3.setTitle("21일에 술 먹을 사람 구합니다.33");
        post3.setUsing_data(true);
        post3.setUser(user);

        postRepository.save(post3);

        List<Post> posts = userRepository.findById(user.getEmail()).get().getPostList();

        for(Post post: posts){
            System.out.println(post);
        }

        postRepository.delete(post3);

        List<Post> newPosts = userRepository.findById(user.getEmail()).get().getPostList();

        for(Post post: newPosts){
            System.out.println(post);
        }
    }
}
