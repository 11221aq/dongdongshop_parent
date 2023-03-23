package com.dongdongshop.controller;

import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.model.TeamsUser;
import com.dongdongshop.service.TeamsUserService;
import com.dongdongshop.vo.TeamsUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("teamsUser")
public class TeamsUserController {

    @Autowired
    private TeamsUserService service;

    @PostMapping("addTeamsUser")
    public Result addTeamsUser(@RequestBody TeamsUserVO vo) {
        try {
            service.addTeamsUser(vo);
            return Result.ok();
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
