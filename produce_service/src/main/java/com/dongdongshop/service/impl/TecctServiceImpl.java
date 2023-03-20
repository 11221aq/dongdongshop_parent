package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.model.Tecct;
import com.dongdongshop.model.Tecpro;
import com.dongdongshop.service.TecctService;
import com.dongdongshop.mapper.TecctMapper;
import com.dongdongshop.vo.ProcessVO;
import com.dongdongshop.vo.TecctVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 29555
* @description 针对表【tecct(工艺物料产品中间表)】的数据库操作Service实现
* @createDate 2023-03-20 17:00:39
*/
@Service
public class TecctServiceImpl extends ServiceImpl<TecctMapper, Tecct>
    implements TecctService{

    @Resource
    private TecctMapper mapper;

    @Override
    public List<Long> getTecctList(Long pid) {
        List<Long> l = new ArrayList<Long>();
        mapper.selectList(new LambdaQueryWrapper<Tecct>()
                .eq(Tecct::getPid,pid)
        )
                .stream().forEach(t -> {
            l.add(t.getPid());
        });
        return l;
    }

    @Override
    public void addDate(TecctVO vo) {
        Tecct c = new Tecct();
        BeanUtils.copyProperties(vo,c);
        mapper.insert(c);
    }

    @Override
    public void updateDate(TecctVO vo) {
        Tecct c = new Tecct();
        BeanUtils.copyProperties(vo,c);
        mapper.updateById(c);
    }

    @Override
    public void deleteDate(String productid) {
        mapper.delete(new LambdaQueryWrapper<Tecct>().eq(Tecct::getProductid,productid));
    }

    @Override
    public void deleteTecct(Long tid) {
        mapper.delete(new LambdaQueryWrapper<Tecct>().eq(Tecct::getPid,tid));
    }
}




