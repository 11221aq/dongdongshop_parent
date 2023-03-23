package com.dongdongshop.service;

import com.dongdongshop.model.Teams;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.vo.TeamsVO;

import java.util.List;

/**
* @author 29555
* @description 针对表【teams(班组表)】的数据库操作Service
* @createDate 2023-03-23 10:03:42
*/
public interface TeamsService extends IService<Teams> {

    List<TeamsVO> getTeamsList(TeamsVO teamsVO);

    void deleteById(Long id);
}
