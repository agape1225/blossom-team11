package com.springboot.jpa.controller;

import com.springboot.jpa.data.dto.UserDto;
import com.springboot.jpa.data.dto.UserFormDto;
import com.springboot.jpa.data.entity.User;
import com.springboot.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

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

}
