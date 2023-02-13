package com.springboot.jpa.data.repository;

import com.springboot.jpa.data.entity.Post;
import com.springboot.jpa.data.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

        Post post = new Post();
        post.setContent("나랑 술 먹을 사람 구함~");
        post.setTitle("21일에 술 먹을 사람 구합니다.");
        post.setUsing_data(true);
        post.setUser(user);

        postRepository.save(post);

        System.out.println(
                "post : " + postRepository.findById(1L)
                        .orElseThrow(RuntimeException::new).getUser()
        );


    }
}
