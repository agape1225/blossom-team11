package com.springboot.jpa.service.impl;

import com.springboot.jpa.constant.Role;
import com.springboot.jpa.data.dao.UserDAO;
import com.springboot.jpa.data.dto.LoginDto;
import com.springboot.jpa.data.dto.LoginResponseDto;
import com.springboot.jpa.data.dto.UserDto;
import com.springboot.jpa.data.dto.UserFormDto;
import com.springboot.jpa.data.entity.User;
import com.springboot.jpa.data.repository.UnivRepository;
import com.springboot.jpa.data.repository.UserRepository;
import com.springboot.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    UserDAO userDAO;

    private final UserRepository userRepository;

    private final UnivRepository univRepository;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, UserRepository userRepository, UnivRepository univRepository){
        this.userDAO = userDAO;
        this.userRepository = userRepository;
        this.univRepository = univRepository;
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

    @Override
    public UserDto signup(UserFormDto user) {
        validateDuplicateUser(user);

        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setKakaoId(user.getKakaoId());
        newUser.setAge(user.getAge());
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());
        newUser.setRole(Role.ROLE_USER);
        newUser.setUniv(validateEmail(user));

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User updatedUser = userDAO.insertUser(newUser);
        UserDto responsedUser = new UserDto();
        responsedUser.setAge(updatedUser.getAge());
        responsedUser.setEmail(updatedUser.getEmail());
        responsedUser.setName(updatedUser.getName());
        responsedUser.setKakaoId(updatedUser.getKakaoId());

        //responsedUser.setPassword(updatedUser.getPassword());

        return responsedUser;
    }

    @Override
    public LoginResponseDto getLoginUser(String email) {
        User selectedUser = userDAO.selectUser(email);
        LoginResponseDto responseUser = new LoginResponseDto();
        responseUser.setEmail(selectedUser.getEmail());
        responseUser.setPassword(selectedUser.getPassword());
        responseUser.setRole(selectedUser.getRole());

        return responseUser;
    }

    private void validateDuplicateUser(UserFormDto user) {
        User findUser = userRepository.findByEmail(user.getEmail());
        if(findUser != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    private String validateEmail(UserFormDto user){
        String email = user.getEmail();
        String domain = email.substring(email.indexOf("@")+1); // 입력 유저 도메인

        if (univRepository.findByDomain(domain) == null){
            throw new NullPointerException("해당하는 대학이 존재하지 않습니다.");
        }else{
            return univRepository.findByDomain(domain).getUnivNm();
        }
    }
}
