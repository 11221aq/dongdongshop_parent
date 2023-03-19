package com.dongdongshop.tactics;

import com.dongdongshop.annotate.CodePart;
import com.dongdongshop.vo.CodePartVO;
import org.springframework.stereotype.Component;

@Component
@CodePart(partType = "FIXCHAR")
public class FixImpl implements CodeStrategyHandler {//固定字符

    @Override
    public String getCode(CodePartVO partVO) {
        return partVO.getFixCharacter();
    }
}
