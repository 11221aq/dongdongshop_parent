package com.dongdongshop.service;

import com.dongdongshop.model.CodeRule;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.vo.CodeRuleVO;

import java.util.List;

/**
* @author 29555
* @description 针对表【sys_auto_code_rule(编码生成规则表)】的数据库操作Service
* @createDate 2023-03-18 10:48:04
*/
public interface CodeRuleService extends IService<CodeRule> {

    List<CodeRuleVO> getCodeRuleList(CodeRuleVO codeRuleVO);

    void addCodeRule(CodeRuleVO code);

    CodeRuleVO getCodeRuleById(Long ruleId);

    void updateCodeRule(CodeRuleVO code);

    void deleteCudeRule(Long ruleId);
}
