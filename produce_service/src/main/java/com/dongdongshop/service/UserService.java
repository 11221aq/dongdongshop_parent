package com.dongdongshop.service;

import com.dongdongshop.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.vo.UserVO;

import java.util.Date;
import java.util.List;

/**
* @author 29555
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2023-03-22 12:05:10
*/
public interface UserService extends IService<User> {

    List<UserVO> getUserList(UserVO userVO, Date endTime);
}
