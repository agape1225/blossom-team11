package com.springboot.jpa.service;

import com.springboot.jpa.data.dto.UserDto;
import com.springboot.jpa.data.entity.User;

public interface UserService {
    //get save change delete

    UserDto getUser(String email);
    UserDto saveUser(UserDto user);
    UserDto changeUser(UserDto user) throws Exception;
    void deleteUser(String email) throws Exception;
}
