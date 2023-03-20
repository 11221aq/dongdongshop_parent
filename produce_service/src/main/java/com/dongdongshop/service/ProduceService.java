package com.dongdongshop.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dongdongshop.model. Produce;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.vo.ProduceVO;
import com.dongdongshop.vo.ProductVO;

import java.util.List;

/**
* @author 29555
* @description 针对表【 produce】的数据库操作Service
* @createDate 2023-03-17 11:20:26
*/
public interface  ProduceService extends IService< Produce> {

    List<ProduceVO> getProduceList(ProduceVO vo);

    void addProduce(ProduceVO vo);

    ProduceVO getProduceById(String pid);

    void updateProduce(ProduceVO vo);

    void deleteProduce(String pid);

    List<ProductVO> getProListByPid(Long pid);
}
