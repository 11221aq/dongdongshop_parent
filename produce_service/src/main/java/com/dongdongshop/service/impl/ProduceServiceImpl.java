package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.model. Produce;
import com.dongdongshop.service. ProduceService;
import com.dongdongshop.mapper. ProduceMapper;
import com.dongdongshop.vo.ProduceVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
* @author 29555
* @description 针对表【 produce】的数据库操作Service实现
* @createDate 2023-03-17 11:20:26
*/
@Service
public class  ProduceServiceImpl extends ServiceImpl< ProduceMapper,  Produce> implements  ProduceService{

    @Resource
    private ProduceMapper produceMapper;

    @Override
    public List<ProduceVO> getProduceList(ProduceVO vo) {
        LambdaQueryWrapper<Produce> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(vo.getPid() != null ,Produce::getPid,vo.getPid());
        wrapper.like(vo.getPname() != null,Produce::getPname,vo.getPname());
        wrapper.like(vo.getPsid() != null,Produce::getPsid,vo.getPsid());
        wrapper.like(vo.getProductOrder() != null,Produce:: getProductOrder,vo.getProductOrder());
        wrapper.like(vo.getProductName() != null,Produce::getProductName,vo.getProductName());
        wrapper.like(vo.getCustomerCode() != null,Produce::getCustomerCode,vo.getCustomerCode());
        wrapper.like(vo.getCustomerName() != null,Produce::getCustomerName,vo.getCustomerName());
        wrapper.like(vo.getNeedTime() != null,Produce::getNeedTime,vo.getNeedTime());
        List<ProduceVO> collect =  produceMapper.selectList(wrapper).stream().map(p -> {
            ProduceVO produceVO = new ProduceVO();
            BeanUtils.copyProperties(p, produceVO);
            return produceVO;
        }).collect(Collectors.toList());
        return getListJSON(collect, "0");
    }

    private List<ProduceVO> getListJSON(List<ProduceVO> collect,String parentId){
        List<ProduceVO> list = new ArrayList<>();
        for (ProduceVO produceVO : collect) {
            if(Objects.equals(produceVO.getParentid(),parentId)){
                list.add(produceVO);
                produceVO.setVoList(getListJSON(collect,produceVO.getPid()));
            }
        }

        return list;
    }

    @Override
    public void addProduce(ProduceVO vo) {
        Produce p = new Produce();
        BeanUtils.copyProperties(vo,p);
        produceMapper.insert(p);
    }

    @Override
    public ProduceVO getProduceById(String pid) {
        Produce produce = produceMapper.selectById(pid);
        ProduceVO vo = new ProduceVO();
        BeanUtils.copyProperties(produce,vo);
        return vo;
    }

    @Override
    public void updateProduce(ProduceVO vo) {
        Produce p = new Produce();
        BeanUtils.copyProperties(vo,p);
        produceMapper.updateById(p);
    }

    @Override
    public void deleteProduce(String pid) {
        produceMapper.deleteById(pid);

        LambdaQueryWrapper<Produce> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(pid!=null,Produce::getParentid,pid);
        List<Produce> produces = produceMapper.selectList(wrapper);

        if(produces.size() != 0){
            for (Produce produce : produces) {
                deleteProduce(produce.getPid());
            }
        }else{
            return;
        }
    }

}




