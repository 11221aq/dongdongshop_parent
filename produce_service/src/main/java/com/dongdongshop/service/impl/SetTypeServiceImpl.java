package com.dongdongshop.service.impl;

import cn.hutool.core.lang.hash.Hash;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.model.*;
import com.dongdongshop.service.*;
import com.dongdongshop.mapper.SetTypeMapper;
import com.dongdongshop.vo.ClassesVO;
import com.dongdongshop.vo.SetTypeVO;
import com.dongdongshop.vo.TeamsSetVO;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
* @author 29555
* @description 针对表【set_type(排班计划)】的数据库操作Service实现
* @createDate 2023-03-23 18:01:12
*/
@Service
public class SetTypeServiceImpl extends ServiceImpl<SetTypeMapper, SetType>
    implements SetTypeService{

    @Resource
    private SetTypeMapper mapper;

    @Resource
    private ClassesService service;

    @Resource
    private TeamsSetService setService;

    @Resource
    private TeamsUserService userService;
    
    @Resource
    private UserService uservice;

    @Override
    public List<SetTypeVO> getSetTypeList(SetTypeVO vo) {
        List<SetTypeVO> collect = mapper.selectList(new LambdaQueryWrapper<SetType>()
                .eq(vo.getTeamsType() != null, SetType::getTeamsType, vo.getTeamsType())
                .like(vo.getCode() != null, SetType::getCode, vo.getCode())
                .like(vo.getName() != null, SetType::getName, vo.getName())
                .eq(vo.getStartTime() != null, SetType::getStartTime, vo.getStartTime())
                .eq(vo.getEndTime() != null, SetType::getEndTime, vo.getEndTime())
        ).stream().map(s -> {
            SetTypeVO v = new SetTypeVO();
            BeanUtils.copyProperties(s, v);
            return v;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    @Transactional
    public void addSetType(SetTypeVO vo) {
        SetType s = new SetType();
        BeanUtils.copyProperties(vo,s);
        mapper.insert(s);
        service.insertDate(s);
    }


    @Override
    public SetTypeVO getSetTypeById(Long id) {
        SetType setType = mapper.selectById(id);
        SetTypeVO vo = new SetTypeVO();
        BeanUtils.copyProperties(setType, vo);
        List<ClassesVO> collect = service.list(new LambdaQueryWrapper<Classes>()
                .eq(Classes::getSid, id)
        ).stream().map(c -> {
            ClassesVO v = new ClassesVO();
            BeanUtils.copyProperties(c, v);
            return v;
        }).collect(Collectors.toList());
        vo.setClassesList(collect);
        List<TeamsSetVO> teamsSetVOS = setService.list(new LambdaQueryWrapper<TeamsSet>()//排班id对应的班组
                .eq(TeamsSet::getSid, id)
        ).stream().map(t -> {
            TeamsSetVO v = new TeamsSetVO();
            BeanUtils.copyProperties(t, v);
            return v;
        }).collect(Collectors.toList());
        Map<Long, List<TeamsUser>> listMap = userService.list().stream().collect(Collectors.groupingBy(TeamsUser::getTid));
        Map<Long,Object> map = new HashMap<>();
        for (TeamsSetVO teamsSetVO : teamsSetVOS) {
            List<Long> userIds = new ArrayList<>();
            List<TeamsUser> teamsUsers = listMap.get(teamsSetVO.getTid());
            for (TeamsUser teamsUser : teamsUsers) {
                userIds.add(teamsUser.getUid());
            }
            List<User> users = uservice.listByIds(userIds);
            map.put(teamsSetVO.getTid(),users);
        }
        vo.setMap(map);
        return vo;
    }
}




