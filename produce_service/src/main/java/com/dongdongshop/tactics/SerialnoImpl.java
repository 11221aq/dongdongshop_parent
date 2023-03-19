package com.dongdongshop.tactics;

import cn.hutool.core.date.DateUtil;
import com.dongdongshop.annotate.CodePart;
import com.dongdongshop.service.CodePartService;
import com.dongdongshop.vo.CodePartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
@CodePart(partType = "SERIALNO")
public class SerialnoImpl implements CodeStrategyHandler {

    @Autowired
    private CodePartService partService;

    @Override
    public String getCode(CodePartVO partVO) {//流水号
        if (Objects.equals(partVO.getCycleFlag(), "N")) {//如果不循环
            return getNum(partVO);
        }

        Date date = new Date();

        long l = 0l;
        if(Objects.equals(partVO.getCycleMethod(), "YEAR")){//按照年循环

            l = DateUtil.betweenYear(partVO.getUpdateTime(), date, true);

        } else if(Objects.equals(partVO.getCycleMethod(), "MONTH")){//按照月循环

            l = DateUtil.betweenMonth(partVO.getUpdateTime(), date, true);

        } else if(Objects.equals(partVO.getCycleMethod(), "DAY")){//按照天循环

            l = DateUtil.betweenDay(partVO.getUpdateTime(), date, true);

        } else if(Objects.equals(partVO.getCycleMethod(), "HOUR")){//按照时循环
            long time = partVO.getUpdateTime().getTime();//修改毫秒值
            long dateTime = date.getTime();//当前毫秒值
            l = (dateTime - time) / (1000 * 60 * 60);
        } else if(Objects.equals(partVO.getCycleMethod(), "MINITE")){//按照分循环
            long time = partVO.getUpdateTime().getTime();//修改毫秒值
            long dateTime = date.getTime();//当前毫秒值
            l = (dateTime - time) / (1000 * 60);
        }
        if(l > 1){
            updateNowTime(partVO);//改为默认数
            return getNum(partVO);
        }else {
            return getNum(partVO);
        }

    }
    private String getNum(CodePartVO partVO) {
        int i = partVO.getSeriaNowNo() + partVO.getSeriaStep();
        String num = i + "";
        int x = partVO.getPartLength() - num.length();
        if(x > 0){
            for (int j = 0; j < x; j++) {
                num = "0" + num;
            }
        }

        CodePartVO vo = new CodePartVO();
        vo.setPartId(partVO.getPartId());
        vo.setSeriaNowNo(i);
        partService.updateCodePart(vo);
        return num;
    }
    private void updateNowTime(CodePartVO partVO) {
        CodePartVO vo = new CodePartVO();
        vo.setPartId(partVO.getPartId());
        vo.setSeriaNowNo(partVO.getSeriaStartNo() - partVO.getSeriaStep());
        vo.setUpdateTime(new Date());
        partService.updateCodePart(vo);
    }
}
