package com.dk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public  class JavaMailConfig {
    @Bean
    public static JavaMailSender javaMailSender(){
        JavaMailSenderImpl javaMailSender=new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.163.com");
        javaMailSender.setPort(25);
        javaMailSender.setUsername("z19819462057@163.com");//用户名
        javaMailSender.setPassword("JYSPLONFABHGZVZU");
        return javaMailSender;
    }

    @Bean
    public static SimpleMailMessage simpleMailMessage(){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom("z19819462057@163.com");//发送者
        simpleMailMessage.setSubject("spring_mail");
        return simpleMailMessage;
    }
}