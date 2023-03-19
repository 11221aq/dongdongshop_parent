package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.model.CodePart;
import com.dongdongshop.service.CodePartService;
import com.dongdongshop.mapper.CodePartMapper;
import com.dongdongshop.vo.CodePartVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
* @author 29555
* @description 针对表【sys_auto_code_part(编码生成规则组成表)】的数据库操作Service实现
* @createDate 2023-03-18 10:47:57
*/
@Service
public class CodePartServiceImpl extends ServiceImpl<CodePartMapper, CodePart>
    implements CodePartService{

    @Resource
    private CodePartMapper mapper;

    @Override
    public List<CodePartVO> getgetCodePartList(CodePartVO vo) {
        LambdaQueryWrapper<CodePart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(vo.getRuleId() != null,CodePart::getRuleId,vo.getRuleId());
        wrapper.like(vo.getPartCode() != null,CodePart::getPartCode,vo.getPartCode());
        wrapper.like(vo.getPartName() != null,CodePart::getPartName,vo.getPartName());
        wrapper.orderByAsc(CodePart::getPartIndex,CodePart::getCreateTime);
        List<CodePartVO> collect = mapper.selectList(wrapper).stream().map(c -> {
            CodePartVO codePartVO = new CodePartVO();
            BeanUtils.copyProperties(c, codePartVO);
            return codePartVO;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public void addCodePart(CodePartVO vo) {
        CodePart part = new CodePart();
        BeanUtils.copyProperties(vo, part);
        if(Objects.equals(part.getPartType(),"SERIALNO")){
            part.setSeriaNowNo(part.getSeriaStartNo() - part.getSeriaStep());
        }
        part.setCreateTime(new Date());
        part.setUpdateTime(new Date());
        mapper.insert(part);
    }

    @Override
    public CodePartVO getCodePartById(Long partId) {
        CodePart part = mapper.selectById(partId);
        CodePartVO codePartVO = new CodePartVO();
        BeanUtils.copyProperties(part, codePartVO);
        return codePartVO;
    }

    @Override
    public void updateCodePart(CodePartVO vo) {
        CodePart part = new CodePart();
        BeanUtils.copyProperties(vo, part);
        mapper.updateById(part);
    }

    @Override
    public void deleteCodePartById(Long partId) {
        mapper.deleteById(partId);
    }

}




