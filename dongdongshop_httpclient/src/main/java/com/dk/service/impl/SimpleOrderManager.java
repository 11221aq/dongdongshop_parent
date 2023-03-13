package com.dk.service.impl;

import com.dk.service.OrderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * 定义实现类
 */
@Service
public class SimpleOrderManager implements OrderManager {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SimpleMailMessage simpleMailMessage;


    @Override
    public void sendMail(String userEmail, String password) {
        simpleMailMessage.setTo(userEmail);//发送给谁
        simpleMailMessage.setText("您的新密码为："+password + "，请及时修改！");
        javaMailSender.send(simpleMailMessage);
    }
}