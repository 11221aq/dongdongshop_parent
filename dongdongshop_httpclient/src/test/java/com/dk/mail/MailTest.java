package com.dk.mail;

import com.dk.service.OrderManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
public class MailTest {

    @Autowired
    private OrderManager orderManager;

    //        * 1.注入配置类
    @Autowired
    private JavaMailSender javaMailSender;

//    @Test
//    public void testSendMail() {//发送普通邮件
//        orderManager.placeOrder();
//    }

    @Test
    public void testSendMail2() throws MessagingException {//发送带附件的邮件
//        * 2.获取邮件对象
        MimeMessage message = javaMailSender.createMimeMessage();
//        * 3.设置邮件主题
        message.setSubject("邮件主题");
//        * 4.创建带有附件的消息帮助类                      message      是否认证         编码格式
        MimeMessageHelper helper = new MimeMessageHelper(message, true,"utf-8");
//        * 5.设置邮件接收者
        helper.setTo("2955503537@qq.com");
//        * 6.设置发送者
        helper.setFrom("z19819462057@163.com");
//        * 7.设置邮件内容
        helper.setText("邮件内容");
//        * 8.设置邮件附件
        File file = new File("D:\\toImg\\img\\product\\size4\\1a276fb64f35d765c941aade4e436e2d.jpg");
        //                   文件名           文件
//        * 9.添加附件
        helper.addAttachment(file.getName(),file);
//        * 10.发送邮件
        javaMailSender.send(message);
    }

}
