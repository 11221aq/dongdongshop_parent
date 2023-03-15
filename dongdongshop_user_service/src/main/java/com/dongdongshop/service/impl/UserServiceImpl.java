package com.dongdongshop.service.impl;

import cn.hutool.http.HttpUtil;
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
import java.util.*;

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

    @Override
    public void retainCustomers() {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        Date date = new Date();//获取当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        criteria.andCreatedLessThan(new Date(calendar.getTimeInMillis()));
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.DAY_OF_YEAR, -7);
        criteria.andLastLoginTimeLessThanOrEqualTo(new Date(ca.getTimeInMillis()));
        userMapper.selectByExample(example).stream().forEach(user -> {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("userEmail",user.getEmail());
            HttpUtil.post("http://127.0.0.1:8094/email/holdEmail", map);
        });
    }


    public void weiboLogin(String username,String pass) {
        TbUser user = new TbUser();
        user.setUsername(username);
        user.setPassword(pass);
        String salt = ShiroUtils.generateSalt(6);
        String password = ShiroUtils.encryptPassword("MD5", user.getPassword(), salt, 10);
        user.setPassword(password);
        user.setSalt(salt);
        user.setUpdated(new Date());
        userMapper.insertSelective(user);
    }

}
