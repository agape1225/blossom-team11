package com.springboot.jpa.data.repository;

import com.springboot.jpa.data.dto.UnivDto;
import com.springboot.jpa.data.dto.UnivResponseDto;
import com.springboot.jpa.data.entity.Univ;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class univRepositoryTest {

    @Autowired
    UnivRepository univRepository;

    public void createItemList(){
        for(int i=1; i<=10; i++){
            Univ univ = new Univ();
            univ.setUnivNm("대학" + i);
            univ.setDomain("domain.ac.kr");
            univ.setLogo("aaaa");

            Univ savedUniv = univRepository.save(univ);

            System.out.println(savedUniv.toString());
        }
    }

    @Test
    @DisplayName("이름 검색")
    public void findByUnivNmTest(){
        createItemList();

        System.out.println(univRepository.findByUnivNm("대학1"));
    }
}