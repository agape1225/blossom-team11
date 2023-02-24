package com.springboot.jpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class MailSenderServiceImpl {
    private final JavaMailSender javaMailSender;
    private SpringTemplateEngine templateEngine;

    @Autowired
    public MailSenderServiceImpl(JavaMailSender javaMailSender, SpringTemplateEngine springTemplateEngine){
        this.javaMailSender = javaMailSender;
        this.templateEngine = springTemplateEngine;
    }

    public void sendAcceptMail(String email, String content) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject("강남대학교 멋쟁이사자처럼 서류전형 결과 공지");

        //템플릿에 전달할 데이터 설정
        Context context = new Context();
        /*context.setVariable("name", mailInfo.getName());
        context.setVariable("interviewDate", mailInfo.getInterviewDate());
        context.setVariable("interviewLocation", mailInfo.getInterviewLocation());
        context.setVariable("interviewTime", mailInfo.getInterviewTime());*/

        //메일 내용 설정 : 템플릿 프로세스
        String html = templateEngine.process("verificationMail.html",context);
        helper.setText(html, true);

        //helper.addInline("image1", new ClassPathResource("templates/images/_.png"));
        //helper.addInline("image2", new ClassPathResource("templates/images/.jpg"));

        //메일 보내기
        javaMailSender.send(message);
    }
}
