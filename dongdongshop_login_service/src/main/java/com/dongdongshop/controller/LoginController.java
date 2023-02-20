package com.dongdongshop.controller;

import com.alibaba.fastjson.JSONObject;
import com.dongdongshop.data.Result;
import com.dongdongshop.dto.SellerDTO;
import com.dongdongshop.dto.UserDTO;
import com.dongdongshop.em.LoginEnum;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.exception.SellerLoginException;
import com.dongdongshop.data.UserVo;
import com.dongdongshop.token.LoginToken;
import com.dongdongshop.util.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public Result checkLogin(@RequestParam String userName,@RequestParam String passWord){
        Subject subject = SecurityUtils.getSubject();
        LoginToken token = new LoginToken(userName,passWord,LoginEnum.USER);
        try {
            subject.login(token);
        }catch (UnknownAccountException e1){
            return Result.result(ResultEnum.LOGIN_ERROR).setData(e1.getMessage());
        }catch (IncorrectCredentialsException e2){
            return Result.result(ResultEnum.LOGIN_ERROR).setData(e2.getMessage());
        }

        UserDTO userDTO = (UserDTO)subject.getPrincipal();
        UserVo vo = new UserVo();
        vo.setUsername(userDTO.getUname());
        vo.setUserId(userDTO.getUid().toString());
        String jwt = jwtUtil.createJwt(vo.getUserId(), JSONObject.toJSONString(vo),"role");
        return Result.ok().setData(jwt);
    }

    @PostMapping("sellerLogin")
    public Result sellerLogin(@RequestParam String userName,@RequestParam String passWord){
        Subject subject = SecurityUtils.getSubject();
        LoginToken token = new LoginToken(userName,passWord,LoginEnum.SELLER);
        try {
            subject.login(token);
        }catch (UnknownAccountException e1){
            return Result.result(ResultEnum.LOGIN_ERROR).setData(e1.getMessage());
        }catch (IncorrectCredentialsException e2){
            return Result.result(ResultEnum.LOGIN_ERROR).setData(e2.getMessage());
        }catch (SellerLoginException e3){
            return Result.result(ResultEnum.TO_BE_REVIEWED).setData(e3.getMessage());
        }

        SellerDTO sellerDTO = (SellerDTO)subject.getPrincipal();
        UserVo vo = new UserVo();
        vo.setUsername(sellerDTO.getName());
        vo.setUserId(sellerDTO.getSellerId());
        String jwt = jwtUtil.createJwt(vo.getUserId(), JSONObject.toJSONString(vo),"role");
        return Result.ok().setData(jwt);
    }

}
