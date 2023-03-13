package com.dongdongshop.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.dongdongshop.data.Result;
import com.dongdongshop.dto.LoginUserDTO;
import com.dongdongshop.model.TbUser;
import com.dongdongshop.service.ForgotService;
import com.dongdongshop.service.UserService;
import com.dongdongshop.util.ShiroUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ForgotServiceImpl implements ForgotService {

    @Autowired
    private UserService userService;

    @Override
    public void forgotPassword(String username) {
        if(StringUtils.isBlank(username)){
            throw new RuntimeException("用户名不能为空");
        }

        TbUser user = userService.getUserByName(username);
        if(user == null){
            throw new RuntimeException("用户名不存在");
        }

        String password = ShiroUtils.generateSalt(8);
        String md5 = ShiroUtils.encryptPassword("MD5", password, user.getSalt(), 10);
        user.setPassword(md5);
        userService.updateUser(user);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userEmail",user.getEmail());
        map.put("password",password);
        String post = HttpUtil.post("http://127.0.0.1:7002/email/sendEmail", map);
        System.out.println(post);
    }
}
