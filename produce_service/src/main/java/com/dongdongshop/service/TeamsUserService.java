package com.dongdongshop.service;

import com.dongdongshop.model.TeamsUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.vo.TeamsUserVO;

/**
* @author 29555
* @description 针对表【teams_user】的数据库操作Service
* @createDate 2023-03-23 16:50:04
*/
public interface TeamsUserService extends IService<TeamsUser> {

    void addTeamsUser(TeamsUserVO vo);
}
