package com.springboot.jpa.controller;

import com.springboot.jpa.service.impl.MailSenderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailSenderController {
    MailSenderServiceImpl mailSenderService;
    @Autowired
    public MailSenderController(MailSenderServiceImpl mailSenderService){
        this.mailSenderService = mailSenderService;
    }

    @GetMapping()
    public String SendMail(String email) throws Exception{
        mailSenderService.sendAcceptMail(email, "hello world");
        return "정상 전송 되었습니다.";
    }

}
