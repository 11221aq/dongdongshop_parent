package com.dongdongshop.test;

import com.dongdongshop.model.CodePart;
import com.dongdongshop.service.CodePartService;
import com.dongdongshop.service.CodeRuleService;
import com.dongdongshop.vo.CodePartVO;
import com.dongdongshop.vo.CodeRuleVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@SpringBootTest
public class MyTest {

    @Autowired
    private CodeRuleService ruleService;

    @Autowired
    private CodePartService partService;

    @Test
    public void test() {
        Long ruleId = 233l;
        CodeRuleVO ruleVO = ruleService.getCodeRuleById(ruleId);
        System.err.println("对象信息为 " + ruleVO);
        CodePartVO codePart = new CodePartVO();
        codePart.setRuleId(ruleId);
        List<CodePartVO> partVOS = partService.getgetCodePartList(codePart);
        System.err.println("集合信息为" + partVOS);
        if (partVOS.size() <= 0) {
            throw new RuntimeException("当前对象没有设置编码规格");
        }
        StringBuilder code = new StringBuilder();

        Date date = new Date();

        for (CodePartVO partVO : partVOS) {
            if (Objects.equals(partVO.getPartType(), "INPUTCHAR")) {//输入字符
                code.append(partVO.getInputCharacter());
                continue;
            }
            if (Objects.equals(partVO.getPartType(), "NOWDATE")) {//当前日期
                SimpleDateFormat sdf = new SimpleDateFormat(partVO.getDateFormat());
                String format = sdf.format(date);
                code.append(format);
                continue;
            }
            if (Objects.equals(partVO.getPartType(), "FIXCHAR")) {//固定字符
                code.append(partVO.getFixCharacter());
                continue;
            }


            if (Objects.equals(partVO.getCycleFlag(), "N")) {//如果不循环
                getNum(code, partVO);
                continue;
            }

            if(Objects.equals(partVO.getCycleMethod(), "YEAR")){//按照年循环
                long time = partVO.getUpdateTime().getTime();//修改毫秒值
                long dateTime = date.getTime();//当前毫秒值
                long year = 1000 * 60 * 60 * 24 * 365;
                if(dateTime - year > time){//超过时间
                    updateNowTime(partVO);//改为默认数
                    getNum(code, partVO);
                }else {
                    getNum(code, partVO);
                }
            } else if(Objects.equals(partVO.getCycleMethod(), "MONTH")){//按照年循环
                long time = partVO.getUpdateTime().getTime();//修改毫秒值
                long dateTime = date.getTime();//当前毫秒值
                long year = 1000 * 60 * 60 * 24 * 30;
                if(dateTime - year > time){//超过时间
                    updateNowTime(partVO);//改为默认数
                    getNum(code, partVO);
                }else {
                    getNum(code, partVO);
                }
            } else if(Objects.equals(partVO.getCycleMethod(), "DAY")){//按照年循环
                long time = partVO.getUpdateTime().getTime();//修改毫秒值
                long dateTime = date.getTime();//当前毫秒值
                long year = 1000 * 60 * 60 * 24;
                if(dateTime - year > time){//超过时间
                    updateNowTime(partVO);//改为默认数
                    getNum(code, partVO);
                }else {
                    getNum(code, partVO);
                }
            } else if(Objects.equals(partVO.getCycleMethod(), "HOUR")){//按照年循环
                long time = partVO.getUpdateTime().getTime();//修改毫秒值
                long dateTime = date.getTime();//当前毫秒值
                long year = 1000 * 60 * 60 * 24;
                if(dateTime - year > time){//超过时间
                    updateNowTime(partVO);//改为默认数
                    getNum(code, partVO);
                }else {
                    getNum(code, partVO);
                }
            } else if(Objects.equals(partVO.getCycleMethod(), "MINITE")){//按照年循环
                long time = partVO.getUpdateTime().getTime();//修改毫秒值
                long dateTime = date.getTime();//当前毫秒值
                long year = 1000 * 60 * 60 * 24;
                if(dateTime - year > time){//超过时间
                    updateNowTime(partVO);//改为默认数
                    getNum(code, partVO);
                }else {
                    getNum(code, partVO);
                }
            }else {
                code.append(123);
            }

        }

//        String s = code.substring(0, code.length() - 1);//过长则减去

        if(Objects.equals(ruleVO.getIsPadded(),"Y")){//补齐
            if((ruleVO.getMaxLength() - code.length()) > 0){
                String substring = ruleVO.getPaddedChar().substring(0, (ruleVO.getMaxLength() - code.length()));
                if(Objects.equals(ruleVO.getPaddedMethod(),"L")){
                    code.insert(0,substring);
                }else{
                    code.append(substring);
                }
            }else {
                code.substring(0,ruleVO.getMaxLength() - 1);
            }
        }
        System.err.println(code);//16位
    }

    private void getNum(StringBuilder code, CodePartVO partVO) {
        int i = partVO.getSeriaNowNo() + partVO.getSeriaStep();
        code.append(i);
        CodePartVO vo = new CodePartVO();
        vo.setPartId(partVO.getPartId());
        vo.setSeriaNowNo(i);
        partService.updateCodePart(vo);
    }

    private void updateNowTime(CodePartVO partVO) {
        CodePartVO vo = new CodePartVO();
        vo.setPartId(partVO.getPartId());
        partVO.setSeriaNowNo(partVO.getSeriaStartNo() - partVO.getSeriaStep());
        partService.updateCodePart(vo);
    }
}
