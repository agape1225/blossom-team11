package com.springboot.jpa.data.dao.impl;

import com.springboot.jpa.data.dao.UserDAO;
import com.springboot.jpa.data.entity.User;
import com.springboot.jpa.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class UserDAOImpl implements UserDAO {

    UserRepository userRepository;
    @Autowired
    public UserDAOImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User insertUser(User user) {
        User savedUser = userRepository.save(user);

        return savedUser;
    }

    @Override
    public User selectUser(String email) {
        User selectedUser = userRepository.getById(email);

        return selectedUser;
    }

    @Override
    public User updateUser(User user) throws Exception {
        Optional<User> selectedUser = userRepository.findById(user.getEmail());

        User updatedUser = new User();
        if(selectedUser.isPresent()){
            /*updatedUser.setAge(user.getAge());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setKakaoId(user.getKakaoId());
            updatedUser.setName(user.getName());*/

            user.setCreatedAt(selectedUser.get().getCreatedAt());
            user.setUpdatedAt(LocalDateTime.now());

            updatedUser = userRepository.save(user);
        }else{
            throw new Exception();
        }
        return updatedUser;
    }

    @Override
    public void deleteUser(String email) throws Exception {
        Optional<User> selectedUser = userRepository.findById(email);
        if(selectedUser.isPresent()){
            userRepository.delete(selectedUser.get());
        }else {
            throw new Exception();
        }
    }
}
