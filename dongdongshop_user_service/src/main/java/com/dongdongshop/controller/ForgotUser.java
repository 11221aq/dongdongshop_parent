package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.service.ForgotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("forgot")
public class ForgotUser {

    @Autowired
    private ForgotService forgotService;

    @PostMapping("forgotPassword")
    public Result forgotPassword(@RequestParam String username){
        try {
            forgotService.forgotPassword(username);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.error().setData(e.getMessage());
        }
    }
}
