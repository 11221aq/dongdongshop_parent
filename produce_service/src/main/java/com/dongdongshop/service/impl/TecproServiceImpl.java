package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.model.Process;
import com.dongdongshop.model.Tecpro;
import com.dongdongshop.service.TecproService;
import com.dongdongshop.mapper.TecproMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* @author 29555
* @description 针对表【tecpro(工艺工序中间表)】的数据库操作Service实现
* @createDate 2023-03-20 14:40:40
*/
@Service
public class TecproServiceImpl extends ServiceImpl<TecproMapper, Tecpro>
    implements TecproService{

    @Resource
    private TecproMapper mapper;

    @Override
    public List<Long> getProcessList(Long tid) {
        List<Long> l = new ArrayList<Long>();
        mapper.selectList(new LambdaQueryWrapper<Tecpro>()
                .eq(Tecpro::getTid,tid)
                .orderByAsc(Tecpro::getTid)
        )
                .stream().forEach(t -> {
                l.add(t.getPid());
        });
        return l;
    }

    @Override
    public void addContent(Process process) {
        Tecpro t = new Tecpro();
        BeanUtils.copyProperties(process,t);
        mapper.insert(t);
    }

    @Override
    public void deleteByPid(Long pid) {
        mapper.delete(new LambdaQueryWrapper<Tecpro>().eq(Tecpro::getPid,pid));
    }

    @Override
    public void deleteTecpro(Long tid) {
        mapper.delete(new LambdaQueryWrapper<Tecpro>().eq(Tecpro::getTid,tid));
    }
}




