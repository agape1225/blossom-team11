package com.springboot.jpa.service.impl;

import com.springboot.jpa.data.dao.UserDAO;
import com.springboot.jpa.data.dto.UserDto;
import com.springboot.jpa.data.entity.User;
import com.springboot.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Override
    public UserDto getUser(String email) {
        User selectedUser = userDAO.selectUser(email);
        UserDto responseUser = new UserDto();
        responseUser.setKakaoId(selectedUser.getKakaoId());
        responseUser.setName(selectedUser.getName());
        responseUser.setEmail(selectedUser.getEmail());
        responseUser.setAge(selectedUser.getAge());

        return responseUser;
    }

    @Override
    public UserDto saveUser(UserDto user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setKakaoId(user.getKakaoId());
        newUser.setAge(user.getAge());
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());

        User updatedUser = userDAO.insertUser(newUser);
        UserDto responsedUser = new UserDto();
        responsedUser.setAge(updatedUser.getAge());
        responsedUser.setEmail(updatedUser.getEmail());
        responsedUser.setName(updatedUser.getName());
        responsedUser.setKakaoId(updatedUser.getKakaoId());

        return responsedUser;
    }

    @Override
    public UserDto changeUser(UserDto user) throws Exception {
        User changedUser = new User();
        changedUser.setAge(user.getAge());
        changedUser.setEmail(user.getEmail());
        changedUser.setName(user.getName());
        changedUser.setKakaoId(user.getKakaoId());
        changedUser.setUpdatedAt(LocalDateTime.now());
        changedUser.setCreatedAt(LocalDateTime.now());

        User updatedUser = userDAO.updateUser(changedUser);
        UserDto responsedUser = new UserDto();
        responsedUser.setAge(updatedUser.getAge());
        responsedUser.setEmail(updatedUser.getEmail());
        responsedUser.setName(updatedUser.getName());
        responsedUser.setKakaoId(updatedUser.getKakaoId());

        return responsedUser;
    }

    @Override
    public void deleteUser(String email) throws Exception {
        userDAO.deleteUser(email);
    }
}
