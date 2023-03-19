package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.model.Operation;
import com.dongdongshop.service.OperationService;
import com.dongdongshop.mapper.OperationMapper;
import com.dongdongshop.util.PageParms;
import com.dongdongshop.vo.OperationVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 29555
* @description 针对表【operation】的数据库操作Service实现
* @createDate 2023-03-17 21:48:39
*/
@Service
public class OperationServiceImpl extends ServiceImpl<OperationMapper, Operation>
    implements OperationService{

    @Resource
    private OperationMapper mapper;

    @Override
    public Page<OperationVO> getOperationList(PageParms<OperationVO> parms) {
        OperationVO operationVO = parms.getParms();
        Page<Operation> page = new Page<Operation>(parms.getPageNum(),parms.getPageSize());
        LambdaQueryWrapper<Operation> wrapper = new LambdaQueryWrapper();
        wrapper.like(operationVO.getOid() != null,Operation::getOid,operationVO.getOid());
        wrapper.like(operationVO.getOname() != null,Operation::getOname,operationVO.getOname());
        wrapper.like(operationVO.getStatus() != null,Operation::getStatus,operationVO.getStatus());
        mapper.selectPage(page,wrapper);
        Page<OperationVO> p =new Page<>();
        BeanUtils.copyProperties(page,p);
        return p;
    }

    @Override
    public void addOperation(OperationVO vo) {
        Operation operation = new Operation();
        BeanUtils.copyProperties(vo,operation);
        mapper.insert(operation);
    }

    @Override
    public void deleteOperation(String oid) {
        mapper.deleteById(oid);
    }
}




