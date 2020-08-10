package com.diamond.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @Classname EmailService
 * @Description TODO
 * @Date 2020/8/10 16:00
 * @Created by lrf
 */
@Component
public class EmailService {


    @Autowired
    private JavaMailSender mailSender;

    public void sendMsg() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // 邮件发送者，这里不能随便填写，必须是真实的发送邮件的邮箱名称
        mailMessage.setFrom("570723764@qq.com");
        // 邮件接受者
        mailMessage.setTo("lfslrf@gmail.com");
        // 邮件主题
        mailMessage.setSubject("testSubject");
        // 邮件内容
        mailMessage.setText("hello email!");
        // 发送邮件
        mailSender.send(mailMessage);
    }

}

