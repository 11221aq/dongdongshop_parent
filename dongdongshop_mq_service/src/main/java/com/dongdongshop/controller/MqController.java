package com.dongdongshop.controller;


import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("mq")
public class MqController{

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @PostMapping("getPhoneNum")
    public Result getPhoneNum(@RequestParam String phone) {
        try {
            rocketMQTemplate.convertAndSend("phoneNum",phone);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e);
        }
    }

}
