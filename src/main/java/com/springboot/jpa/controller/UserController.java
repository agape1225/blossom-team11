package com.springboot.jpa.controller;

import com.springboot.jpa.data.dto.LoginDto;
import com.springboot.jpa.data.dto.LoginResponseDto;
import com.springboot.jpa.data.dto.UserDto;
import com.springboot.jpa.data.dto.UserFormDto;
import com.springboot.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.springboot.jpa.token.JwtTokenProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider){
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping()
    public ResponseEntity<UserDto> getUser(String email) {
        UserDto user = userService.getUser(email);
        //System.out.println(user);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping()
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        UserDto savedUser = userService.saveUser(user);

        return ResponseEntity.status(HttpStatus.OK).body(savedUser);
    }

    @PutMapping()
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user) throws Exception{
        UserDto updatedUser = userService.changeUser(user);

        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @DeleteMapping()
    public String deleteUser(String email) throws Exception{
        userService.deleteUser(email);
        return "정상적으로 삭제 되었습니다.";
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@Valid @RequestBody UserFormDto user, BindingResult bindingResult) throws BindException {
        if (Objects.equals(user.getEmail(), "")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이메일을 입력하세요.");
        }
        if (Objects.equals(user.getPassword(), "")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호를 입력하세요.");
        }
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        try {
            UserDto savedUser = userService.signup(user);
            return ResponseEntity.status(HttpStatus.OK).body(savedUser);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 가입된 회원입니다.");
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("해당하는 대학이 존재하지 않습니다.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto user) {
        try {
            LoginResponseDto responseUser = userService.getLoginUser(user.getEmail());
            if (!passwordEncoder.matches(user.getPassword(), responseUser.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("잘못된 비밀번호입니다.");
            }
            // 토큰 반환 - 이메일과 권한 정보를 반환
            return ResponseEntity.status(HttpStatus.OK).body(jwtTokenProvider.createToken(user.getEmail(), responseUser.getRole()));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("가입되지 않은 E-MAIL입니다.");
        }
    }

    @PostMapping("/test/user")
    public Map userResponseTest() {
        Map<String, String> result = new HashMap<>();
        result.put("result","user ok");
        return result;
    }

    @PostMapping("/test/admin")
    public Map adminResponseTest() {
        Map<String, String> result = new HashMap<>();
        result.put("result","admin ok");
        return result;
    }

}
