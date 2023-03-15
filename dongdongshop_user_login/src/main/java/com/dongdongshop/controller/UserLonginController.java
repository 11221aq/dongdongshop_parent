package com.dongdongshop.controller;

import com.alibaba.fastjson.JSONObject;
import com.dongdongshop.data.Result;
import com.dongdongshop.data.UserVo;
import com.dongdongshop.dto.LoginUserDTO;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.util.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("userLogin")
public class UserLonginController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public Result userLogin(@RequestParam String username, @RequestParam String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
        }catch (UnknownAccountException e1){
            return Result.result(ResultEnum.LOGIN_ERROR).setData(e1.getMessage());
        }catch (IncorrectCredentialsException e2){
            return Result.result(ResultEnum.LOGIN_ERROR).setData(e2.getMessage());
        }

        LoginUserDTO user = (LoginUserDTO) subject.getPrincipal();
        UserVo vo = new UserVo();
        vo.setUsername(user.getUsername());
        vo.setUserId(user.getId().toString());
        String jwt = jwtUtil.createJwt(vo.getUserId(), JSONObject.toJSONString(vo),"role");
        return Result.ok().setData(jwt);
    }


}
