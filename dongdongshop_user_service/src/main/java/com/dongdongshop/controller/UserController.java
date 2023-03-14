package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.data.UserVo;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.model.TbUser;
import com.dongdongshop.service.UserService;
import com.dongdongshop.vo.UserLonginVO;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("loginUser")
    public Result loginUser(@RequestBody UserLonginVO userVo) {
        try {
            Integer status = userService.loginUser(userVo);
            return Result.ok().setData(status);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @PostMapping("getUserByName")
    public Result getUserByName(@RequestParam String username) {
        try {
            TbUser user = userService.getUserByName(username);
            return Result.ok().setData(user);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

    @XxlJob("RetainCustomers")
    public void retainCustomers(){
        try {
            userService.retainCustomers();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
