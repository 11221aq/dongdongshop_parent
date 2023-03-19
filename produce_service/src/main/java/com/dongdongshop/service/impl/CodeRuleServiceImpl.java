package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.model.CodeRule;
import com.dongdongshop.service.CodeRuleService;
import com.dongdongshop.mapper.CodeRuleMapper;
import com.dongdongshop.vo.CodeRuleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 29555
* @description 针对表【sys_auto_code_rule(编码生成规则表)】的数据库操作Service实现
* @createDate 2023-03-18 10:48:04
*/
@Service
public class CodeRuleServiceImpl extends ServiceImpl<CodeRuleMapper, CodeRule>
    implements CodeRuleService{

    @Resource
    private CodeRuleMapper mapper;

    @Override
    public List<CodeRuleVO> getCodeRuleList(CodeRuleVO codeRuleVO) {
        LambdaQueryWrapper<CodeRule> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(codeRuleVO.getRuleName() != null , CodeRule::getRuleName,codeRuleVO.getRuleName());
        wrapper.like(codeRuleVO.getRuleCode() != null , CodeRule::getRuleCode,codeRuleVO.getRuleCode());
        wrapper.eq(codeRuleVO.getEnableFlag() != null , CodeRule::getEnableFlag,codeRuleVO.getEnableFlag());
        List<CodeRuleVO> collect = mapper.selectList(wrapper).stream().map(r -> {
            CodeRuleVO vo = new CodeRuleVO();
            BeanUtils.copyProperties(r, vo);
            return vo;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public void addCodeRule(CodeRuleVO code) {
        CodeRule vo = new CodeRule();
        BeanUtils.copyProperties(code, vo);
        vo.setCreateTime(new Date());
        vo.setUpdateTime(new Date());
        mapper.insert(vo);
    }

    @Override
    public CodeRuleVO getCodeRuleById(Long ruleId) {
        CodeRule codeRule = mapper.selectById(ruleId);
        CodeRuleVO vo = new CodeRuleVO();
        BeanUtils.copyProperties(codeRule, vo);
        return vo;
    }

    @Override
    public void updateCodeRule(CodeRuleVO code) {
        CodeRule vo = new CodeRule();
        BeanUtils.copyProperties(code, vo);
        mapper.updateById(vo);
    }

    @Override
    public void deleteCudeRule(Long ruleId) {
        mapper.deleteById(ruleId);
    }
}




