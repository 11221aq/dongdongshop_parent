package com.dongdongshop.tactics;

import com.dongdongshop.annotate.CodePart;
import com.dongdongshop.vo.CodePartVO;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@CodePart(partType = "NOWDATE")
public class NowDateImpl implements CodeStrategyHandler {//当前日期
    @Override
    public String getCode(CodePartVO partVO) {
        return new SimpleDateFormat(partVO.getDateFormat()).format(new Date());
    }
}
