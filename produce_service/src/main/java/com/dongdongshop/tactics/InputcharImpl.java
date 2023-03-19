package com.dongdongshop.tactics;

import com.dongdongshop.annotate.CodePart;
import com.dongdongshop.vo.CodePartVO;
import org.springframework.stereotype.Component;

@Component
@CodePart(partType = "INPUTCHAR")
public class InputcharImpl implements CodeStrategyHandler {//输入字符
    @Override
    public String getCode(CodePartVO partVO) {
        return partVO.getInputCharacter();
    }
}
