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
import java.util.Random;

@Service
public class MailSenderServiceImpl {
    private final JavaMailSender javaMailSender;
    private SpringTemplateEngine templateEngine;

    private int certCharLength = 8;

    private final char[] characterTable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };

    @Autowired
    public MailSenderServiceImpl(JavaMailSender javaMailSender, SpringTemplateEngine springTemplateEngine){
        this.javaMailSender = javaMailSender;
        this.templateEngine = springTemplateEngine;
    }

    public void sendAcceptMail(String email, String content) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject("인증메일");

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


    //인증번호 생성
    public String excuteGenerate() {
        Random random = new Random(System.currentTimeMillis());
        int tablelength = characterTable.length;
        StringBuffer buf = new StringBuffer();

        for(int i = 0; i < certCharLength; i++) {
            buf.append(characterTable[random.nextInt(tablelength)]);
        }

        return buf.toString();
    }

    public int getCertCharLength() {
        return certCharLength;
    }

    public void setCertCharLength(int certCharLength) {
        this.certCharLength = certCharLength;
    }



}
