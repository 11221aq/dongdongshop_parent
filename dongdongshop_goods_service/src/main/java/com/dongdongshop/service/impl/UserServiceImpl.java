package com.dongdongshop.service.impl;

import com.dongdongshop.mapper.UserMapper;
import com.dongdongshop.model.User;
import com.dongdongshop.model.UserExample;
import com.dongdongshop.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserByName(String username) {
        UserExample example = new UserExample();
        example.createCriteria().andUnameEqualTo(username);
        List<User> users = userMapper.selectByExample(example);
        return (users ==null || users.isEmpty())?null:users.get(0);
    }
}
