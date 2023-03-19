package com.dongdongshop.service;

import com.dongdongshop.model.Betweena;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.vo.ProductVO;

import java.util.List;

/**
* @author 29555
* @description 针对表【betweena】的数据库操作Service
* @createDate 2023-03-17 22:56:17
*/
public interface BetweenaService extends IService<Betweena> {

    List<ProductVO> getBetList(String pid);
}
