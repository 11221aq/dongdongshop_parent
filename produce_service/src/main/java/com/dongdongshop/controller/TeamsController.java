package com.dongdongshop.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.model.Teams;
import com.dongdongshop.model.TeamsUser;
import com.dongdongshop.model.User;
import com.dongdongshop.service.TeamsService;
import com.dongdongshop.service.TeamsUserService;
import com.dongdongshop.service.UserService;
import com.dongdongshop.vo.TeamsVO;
import com.dongdongshop.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("teams")
public class TeamsController {

    @Autowired
    private TeamsService service;

    @Autowired
    private TeamsUserService teamsUserService;

    @Autowired
    private UserService userService;

    @PostMapping("getTeamsList")
    public Result getTeamsList(@RequestBody TeamsVO teamsVO) {
        try {
            List<TeamsVO> voList = service.getTeamsList(teamsVO);
            return Result.ok().setData(voList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("addTeams")
    public Result addTeams(@RequestBody TeamsVO teamsVO) {
        try {
            Teams teams = new Teams();
            BeanUtils.copyProperties(teamsVO, teams);
            service.save(teams);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("updateTeams")
    public Result updateTeams(@RequestBody TeamsVO teamsVO) {
        try {
            Teams teams = new Teams();
            BeanUtils.copyProperties(teamsVO, teams);
            service.updateById(teams);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("getById")
    public Result getById(@RequestParam Long id) {
        try {
            Teams teams = service.getById(id);
            TeamsVO vo = new TeamsVO();
            BeanUtils.copyProperties(teams, vo);
            List<Long> userIds = new ArrayList<>();
            teamsUserService.list(new LambdaQueryWrapper<TeamsUser>()
                    .eq(TeamsUser::getTid, id)
            ).forEach(tu -> {
                userIds.add(tu.getUid());
            });
            if(userIds != null){
                List<UserVO> collect = userService.listByIds(userIds).stream().map(u -> {
                    UserVO v = new UserVO();
                    BeanUtils.copyProperties(u, v);
                    return v;
                }).collect(Collectors.toList());
                vo.setVoList(collect);
            }
            return Result.ok().setData(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("deleteById")
    public Result deleteById(@RequestParam Long id) {
        try {
           service.deleteById(id);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

}
