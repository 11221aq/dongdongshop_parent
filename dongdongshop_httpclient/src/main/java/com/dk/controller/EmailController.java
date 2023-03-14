package com.dk.controller;

import com.dk.service.OrderManager;
import com.dongdongshop.data.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("email")
public class EmailController {

    @Autowired
    private OrderManager orderManager;

    @PostMapping("sendEmail")
    public Result<String> sendEmail(String userEmail, String password){
        try {
            orderManager.sendMail(userEmail, password);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.error().setData(e.getMessage());
        }
    }

    @PostMapping("holdEmail")
    public void holdEmail(String userEmail){
        try {
            orderManager.holdEmail(userEmail);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
