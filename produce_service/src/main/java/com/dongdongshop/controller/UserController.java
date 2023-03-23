package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.model.User;
import com.dongdongshop.service.UserService;
import com.dongdongshop.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("getUserList")
    public Result getUserList(@RequestBody UserVO userVO, Date endTime) {
        try {
            List<UserVO> userList = service.getUserList(userVO, endTime);
            return Result.ok().setData(userList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("addUser")
    public Result addUser(@RequestBody UserVO userVO) {
        try {
            User user = new User();
            BeanUtils.copyProperties(userVO, user);
            user.setCreatTime(new Date());
            service.save(user);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("updateUser")
    public Result updateUser(@RequestBody UserVO userVO) {
        try {
            User user = new User();
            BeanUtils.copyProperties(userVO, user);
            service.updateById(user);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("getById")
    public Result getById(@RequestParam Long id) {
        try {
            User byId = service.getById(id);
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(byId, userVO);
            return Result.ok().setData(userVO);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("deleteById")
    public Result deleteById(@RequestParam Long id) {
        try {
            service.removeById(id);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

}
