package com.springboot.jpa.service;

import com.springboot.jpa.data.dto.UserDto;
import com.springboot.jpa.data.dto.UserFormDto;
import com.springboot.jpa.data.entity.Univ;
import com.springboot.jpa.service.impl.MailSenderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MailSenderServiceTest {

    @Autowired
    MailSenderServiceImpl mailSenderService;

    @Test
    @DisplayName("메일 전송 테스트")
    public void sendAcceptMailTest() throws Exception{

        mailSenderService.sendAcceptMail("scg9268@naver.com", "hello world");

    }
}
