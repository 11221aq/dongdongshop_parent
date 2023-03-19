package com.dongdongshop.service.impl;

import com.dongdongshop.service.CodePartService;
import com.dongdongshop.service.CodeRuleService;
import com.dongdongshop.service.CodeService;
import com.dongdongshop.tactics.CodeStrategyHandler;
import com.dongdongshop.tactics.TacticsConfig;
import com.dongdongshop.vo.CodePartVO;
import com.dongdongshop.vo.CodeRuleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeRuleService ruleService;

    @Autowired
    private CodePartService partService;

    @Override
    public String getCode(Long ruleId) {
        //Long ruleId = 233l;
        CodeRuleVO ruleVO = ruleService.getCodeRuleById(ruleId);
        CodePartVO codePart = new CodePartVO();
        codePart.setRuleId(ruleId);
        List<CodePartVO> partVOS = partService.getgetCodePartList(codePart);
        if (partVOS.size() <= 0) {
            throw new RuntimeException("当前对象没有设置编码规格");
        }
        StringBuilder code = new StringBuilder();

        for (CodePartVO partVO : partVOS) {
            CodeStrategyHandler codeStrategyHandler = TacticsConfig.map.get(partVO.getPartType());
            String s = codeStrategyHandler.getCode(partVO);
            code.append(s);
        }

        if((ruleVO.getMaxLength() - code.length()) > 0){//长度不够
            if(Objects.equals(ruleVO.getIsPadded(),"Y")) {//补齐
                String substring = ruleVO.getPaddedChar().substring(0, (ruleVO.getMaxLength() - code.length()));
                if(Objects.equals(ruleVO.getPaddedMethod(),"L")){
                    code.insert(0,substring);
                }else{
                    code.append(substring);
                }
            }
        }else if((ruleVO.getMaxLength() - code.length()) < 0){
            code.substring(0,ruleVO.getMaxLength() - 1);
        }
        return code.toString();
    }

}
