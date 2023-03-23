package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.model.TeamsUser;
import com.dongdongshop.service.TeamsUserService;
import com.dongdongshop.mapper.TeamsUserMapper;
import com.dongdongshop.vo.TeamsUserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 29555
* @description 针对表【teams_user】的数据库操作Service实现
* @createDate 2023-03-23 16:50:04
*/
@Service
public class TeamsUserServiceImpl extends ServiceImpl<TeamsUserMapper, TeamsUser>
    implements TeamsUserService{

    @Resource
    private TeamsUserMapper mapper;

    @Override
    public void addTeamsUser(TeamsUserVO vo) {
        TeamsUser teamsUser = mapper.selectOne(new LambdaQueryWrapper<TeamsUser>()
                .eq(TeamsUser::getUid, vo.getUid())
        );
        if(teamsUser != null) {
            throw new RuntimeException("当前用户已添加过班组");
        }
        TeamsUser user = new TeamsUser();
        BeanUtils.copyProperties(vo,user);
        mapper.insert(user);
    }
}




