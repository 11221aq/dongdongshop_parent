package com.dongdongshop.service.impl;

import com.dongdongshop.data.UserVo;
import com.dongdongshop.mapper.TbUserMapper;
import com.dongdongshop.model.TbUser;
import com.dongdongshop.model.TbUserExample;
import com.dongdongshop.service.UserService;
import com.dongdongshop.util.ShiroUtils;
import com.dongdongshop.vo.UserLonginVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private TbUserMapper userMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Integer loginUser(UserLonginVO userVo) {
        String str = (String) redisTemplate.boundValueOps(userVo.getPhone()).get();
        if(StringUtils.isBlank(str)){
            return 2;
        }
        if(!str.equals(userVo.getVerificationCode())){
            return 3;
        }
        String salt = ShiroUtils.generateSalt(6);
        TbUser user = new TbUser();
        BeanUtils.copyProperties(userVo,user);
        String password = ShiroUtils.encryptPassword("MD5", user.getPassword(), salt, 10);
        user.setPassword(password);
        user.setSalt(salt);
        user.setUpdated(new Date());
        return userMapper.insertSelective(user);
    }

    @Override
    public TbUser getUserByName(String username) {
        TbUserExample example = new TbUserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<TbUser> tbUsers = userMapper.selectByExample(example);
        return tbUsers == null || tbUsers.isEmpty() ? null : tbUsers.get(0);
    }

    @Override
    public void updateUser(TbUser user) {
        userMapper.updateByPrimaryKeySelective(user);
    }
}
