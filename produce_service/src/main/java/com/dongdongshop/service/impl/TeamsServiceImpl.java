package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.model.Teams;
import com.dongdongshop.model.TeamsUser;
import com.dongdongshop.service.TeamsService;
import com.dongdongshop.mapper.TeamsMapper;
import com.dongdongshop.service.TeamsUserService;
import com.dongdongshop.vo.TeamsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 29555
* @description 针对表【teams(班组表)】的数据库操作Service实现
* @createDate 2023-03-23 10:03:42
*/
@Service
public class TeamsServiceImpl extends ServiceImpl<TeamsMapper, Teams>
    implements TeamsService{

    @Resource
    private TeamsMapper mapper;


    @Resource
    private TeamsUserService service;

    @Override
    public List<TeamsVO> getTeamsList(TeamsVO teamsVO) {
        List<TeamsVO> collect = mapper.selectList(new LambdaQueryWrapper<Teams>()
                .like(teamsVO.getType() != null, Teams::getType, teamsVO.getType())
                .like(teamsVO.getCode() != null, Teams::getCode, teamsVO.getCode())
                .like(teamsVO.getName() != null, Teams::getName, teamsVO.getName())
        ).stream().map(t -> {
            TeamsVO vo = new TeamsVO();
            BeanUtils.copyProperties(t, vo);
            return vo;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        mapper.deleteById(id);
        service.remove(new LambdaQueryWrapper<TeamsUser>()
            .eq(TeamsUser::getTid,id)
        );
    }
}




