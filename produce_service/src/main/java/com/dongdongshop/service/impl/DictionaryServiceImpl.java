package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.model.Dictionary;
import com.dongdongshop.model.DictionaryDesc;
import com.dongdongshop.service.DictionaryDescService;
import com.dongdongshop.service.DictionaryService;
import com.dongdongshop.mapper.DictionaryMapper;
import com.dongdongshop.vo.DictionaryDescVO;
import com.dongdongshop.vo.DictionaryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
* @author 29555
* @description 针对表【dictionary(字典)】的数据库操作Service实现
* @createDate 2023-03-23 15:51:00
*/
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary>
    implements DictionaryService{

    @Resource
    private DictionaryMapper mapper;

    @Resource
    private DictionaryDescService service;

    @Override
    public List<DictionaryVO> getDictionary(DictionaryVO vo, Date endTime) {
        List<DictionaryVO> collect = mapper.selectList(new LambdaQueryWrapper<Dictionary>()
                .like(vo.getName() != null, Dictionary::getName, vo.getName())
                .like(vo.getCode() != null, Dictionary::getCode, vo.getCode())
                .eq(vo.getStatus() != null, Dictionary::getStatus, vo.getStatus())
                .between(vo.getCreatTime() != null, Dictionary::getCreatTime, vo.getCreatTime(), endTime)
        ).stream().map(d -> {
            DictionaryVO v = new DictionaryVO();
            BeanUtils.copyProperties(d, v);
            return v;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    @Transactional
    public void updateDictionary(DictionaryVO vo) {
        Dictionary d = new Dictionary();
        BeanUtils.copyProperties(vo,d);
        Dictionary dictionary = mapper.selectById(d.getId());
        if(!Objects.equals(dictionary.getCode(),d.getCode())){
            List<DictionaryDesc> list = service.list(new LambdaQueryWrapper<DictionaryDesc>()
                    .eq(DictionaryDesc::getPid, dictionary.getCode())
            );
            for (DictionaryDesc dictionaryDesc : list) {
                dictionaryDesc.setPid(d.getCode());
            }
            service.updateBatchById(list);
        }
        mapper.updateById(d);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Dictionary dictionary = mapper.selectById(id);
        mapper.deleteById(id);
        service.remove(new LambdaQueryWrapper<DictionaryDesc>()
                .eq(DictionaryDesc::getPid, dictionary.getCode())
        );
    }
}




