package com.springboot.jpa.data.dao;

import com.springboot.jpa.data.entity.User;

public interface UserDAO {
    User insertUser(User user);

    User selectUser(String email);

    User updateUser(User user) throws Exception;

    void deleteUser(String email) throws Exception;
}
