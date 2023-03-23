package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.model.DictionaryDesc;
import com.dongdongshop.service.DictionaryDescService;
import com.dongdongshop.mapper.DictionaryDescMapper;
import com.dongdongshop.vo.DictionaryDescVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 29555
* @description 针对表【dictionary_desc(字典明细表)】的数据库操作Service实现
* @createDate 2023-03-23 15:51:05
*/
@Service
public class DictionaryDescServiceImpl extends ServiceImpl<DictionaryDescMapper, DictionaryDesc>
    implements DictionaryDescService{

    @Resource
    private DictionaryDescMapper mapper;

    @Override
    public List<DictionaryDescVO> getDescList(DictionaryDescVO vo) {
        List<DictionaryDescVO> collect = mapper.selectList(new LambdaQueryWrapper<DictionaryDesc>()
                .eq(vo.getPid() != null, DictionaryDesc::getPid, vo.getPid())
                .like(vo.getLabel() != null, DictionaryDesc::getLabel, vo.getLabel())
                .eq(vo.getStatus() != null, DictionaryDesc::getStatus, vo.getStatus())
        ).stream().map(d -> {
            DictionaryDescVO v = new DictionaryDescVO();
            BeanUtils.copyProperties(d, v);
            return v;
        }).collect(Collectors.toList());
        return collect;
    }
}




