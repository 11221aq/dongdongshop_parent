package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.model.Assist;
import com.dongdongshop.service.AssistService;
import com.dongdongshop.mapper.AssistMapper;
import com.dongdongshop.vo.AssistVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 29555
* @description 针对表【assist】的数据库操作Service实现
* @createDate 2023-03-18 09:12:59
*/
@Service
public class AssistServiceImpl extends ServiceImpl<AssistMapper, Assist>
    implements AssistService{

    @Resource
    private AssistMapper mapper;

    @Override
    public List<AssistVO> getAssistList(String pid) {
        List<AssistVO> collect = mapper.selectList(new LambdaQueryWrapper<Assist>().eq(pid != null, Assist::getPid, pid))
                .stream().map(a -> {
                    AssistVO vo = new AssistVO();
                    BeanUtils.copyProperties(a, vo);
                    return vo;
                }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public void deleteByOid(String oid) {
        mapper.delete(new LambdaQueryWrapper<Assist>().eq(oid != null, Assist::getPid, oid));
    }

    @Override
    public void addAssist(AssistVO assistVO) {
        Assist a = new Assist();
        BeanUtils.copyProperties(assistVO,a);
        mapper.insert(a);
    }

    @Override
    public AssistVO getAssistById(Integer aid) {
        Assist a = mapper.selectById(aid);
        AssistVO vo = new AssistVO();
        BeanUtils.copyProperties(a, vo);
        return vo;
    }

    @Override
    public void updateAssist(AssistVO assistVO) {
        Assist a = new Assist();
        BeanUtils.copyProperties(assistVO,a);
        mapper.updateById(a);
    }

    @Override
    public void deleteById(Integer aid) {
        mapper.deleteById(aid);
    }
}




