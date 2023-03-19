package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.model.Betweena;
import com.dongdongshop.service.BetweenaService;
import com.dongdongshop.mapper.BetweenaMapper;
import com.dongdongshop.service.ProductService;
import com.dongdongshop.vo.ProductVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 29555
* @description 针对表【betweena】的数据库操作Service实现
* @createDate 2023-03-17 22:56:17
*/
@Service
public class BetweenaServiceImpl extends ServiceImpl<BetweenaMapper, Betweena>
    implements BetweenaService{

    @Resource
    private BetweenaMapper mapper;

    @Resource
    private ProductService service;

    @Override
    public List<ProductVO> getBetList( String pid) {
        LambdaQueryWrapper<Betweena> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(pid),Betweena::getPid,pid);
        List<String> list = new ArrayList<>();
        mapper.selectList(wrapper).stream().forEach(b -> {
            list.add(b.getCid());
        });
        List<ProductVO> collect = service.getProduct(list).stream().map(p -> {
            ProductVO vo = new ProductVO();
            BeanUtils.copyProperties(p, vo);
            return vo;
        }).collect(Collectors.toList());
        return collect;
    }
}




