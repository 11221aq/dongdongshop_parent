package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.exception.SellerLoginException;
import com.dongdongshop.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("login")
public class LoginController {

    @PostMapping
    public Result checkLogin(@RequestBody UserVo userVo, HttpSession session){
        if(userVo.getUid() != null){
            session.setAttribute("uid", userVo.getUid());
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userVo.getUname(), userVo.getUpwd());
        try {
            subject.login(token);
        }catch (UnknownAccountException e1){
            return Result.result(ResultEnum.LOGIN_ERROR).setData(e1.getMessage());
        }catch (IncorrectCredentialsException e2){
            return Result.result(ResultEnum.LOGIN_ERROR).setData(e2.getMessage());
        }catch (SellerLoginException e3){
            return Result.result(ResultEnum.TO_BE_REVIEWED).setData(e3.getMessage());
        }
        return Result.ok();
    }

}
