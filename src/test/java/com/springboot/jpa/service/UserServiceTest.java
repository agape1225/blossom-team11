package com.springboot.jpa.service;

import com.springboot.jpa.data.dto.UserDto;
import com.springboot.jpa.data.dto.UserFormDto;
import com.springboot.jpa.data.entity.Univ;
import com.springboot.jpa.data.repository.UnivRepository;
import com.springboot.jpa.data.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UnivRepository univRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserFormDto createUser(){
        UserFormDto userFormDto = new UserFormDto();
        userFormDto.setEmail("test@swu.ac.kr");
        userFormDto.setName("이름");
        userFormDto.setAge("20");
        userFormDto.setKakaoId("aaa");
        userFormDto.setPassword("1234");

        return userFormDto;
    }

    public UserFormDto createUser2(){
        UserFormDto userFormDto = new UserFormDto();
        userFormDto.setEmail("testn");
        userFormDto.setName("이름");
        userFormDto.setAge("20");
        userFormDto.setKakaoId("aaa");
        userFormDto.setPassword("1234");

        return userFormDto;
    }


    public Univ createUnivTest(){
        Univ univ = new Univ();

        univ.setUnivNm("서울여자대학교");
        univ.setDomain("swu.ac.kr");
        univ.setLogo("서울여대 로고 주소");

        return univ;
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveUserTest(){
        Univ univ = univRepository.save(createUnivTest());
        System.out.println(univ); // 대학 테이블

        UserFormDto user = createUser(); // 요청 값
        UserDto savedUser = userService.signup(user); // 실제 저장된 데이터

        assertEquals(user.getEmail(), savedUser.getEmail());
        assertEquals(user.getName(), savedUser.getName());
        assertEquals(user.getAge(), savedUser.getAge());
        assertEquals(user.getKakaoId(), savedUser.getKakaoId());
    }

    @Test
    @DisplayName("중복 회원 가입 테스트")
    public void saveDuplicateUserTest(){
        Univ univ = univRepository.save(createUnivTest()); // 학교 정보 저장
        System.out.println(univ);

        UserFormDto user1 = createUser();
        UserFormDto user2 = createUser();
        userService.signup(user1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> {
            userService.signup(user2);});

        assertEquals("이미 가입된 회원입니다.", e.getMessage());
    }

    @Test
    @DisplayName("회원가입 이메일 테스트")
    public void saveUserEmailTest(){
        Univ univ = univRepository.save(createUnivTest()); // 학교 정보 저장
        System.out.println(univ);

        UserFormDto user = createUser2();

        NullPointerException e = assertThrows(NullPointerException.class, () -> {
            userService.signup(user);});

        assertEquals("해당하는 대학이 존재하지 않습니다.", e.getMessage());
    }

}