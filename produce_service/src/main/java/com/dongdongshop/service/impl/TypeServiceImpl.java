package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.model.Type;
import com.dongdongshop.service.TypeService;
import com.dongdongshop.mapper.TypeMapper;
import com.dongdongshop.vo.TypeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 29555
* @description 针对表【type】的数据库操作Service实现
* @createDate 2023-03-17 19:41:28
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

    @Resource
    private TypeMapper typeMapper;

    @Override
    public List<TypeVO> getList() {
        List<TypeVO> collect = typeMapper.selectList(null).stream().map(t -> {
            TypeVO vo = new TypeVO();
            BeanUtils.copyProperties(t, vo);
            return vo;
        }).collect(Collectors.toList());
        return getListJson(collect,0);
    }

    private List<TypeVO> getListJson( List<TypeVO> types,Integer pid) {
        List<TypeVO> list = new ArrayList<TypeVO>();
        for (TypeVO type : types) {
            if(pid.equals(type.getPid())){
                list.add(type);
                type.setVoList(getListJson(types,type.getTid()));
            }
        }
        return list;
    }
}




