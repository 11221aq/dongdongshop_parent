package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.model.User;
import com.dongdongshop.service.UserService;
import com.dongdongshop.mapper.UserMapper;
import com.dongdongshop.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 29555
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2023-03-22 12:05:10
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    private UserMapper mapper;

    @Override
    public List<UserVO> getUserList(UserVO userVO, Date endTime) {
        List<UserVO> collect = mapper.selectList(new LambdaQueryWrapper<User>()
                .like(userVO.getDepartment() != null, User::getDepartment, userVO.getDepartment())
                .like(userVO.getUname() != null, User::getUname, userVO.getUname())
                .like(userVO.getPhone() != null, User::getPhone, userVO.getPhone())
                .eq(userVO.getStatus() != null, User::getStatus, userVO.getStatus())
                .between(userVO.getCreatTime() != null && endTime != null, User::getCreatTime, userVO.getCreatTime(), endTime)
        ).stream().map(u -> {
            UserVO vo = new UserVO();
            BeanUtils.copyProperties(u, vo);
            return vo;
        }).collect(Collectors.toList());
        return collect;
    }
}




