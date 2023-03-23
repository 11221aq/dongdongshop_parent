package com.dongdongshop.test;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.dongdongshop.model.CodePart;
import com.dongdongshop.service.CodePartService;
import com.dongdongshop.service.CodeRuleService;
import com.dongdongshop.vo.CodePartVO;
import com.dongdongshop.vo.CodeRuleVO;
import com.dongdongshop.vo.ProduceVO;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@SpringBootTest
public class MyTest {

    @Autowired
    private CodeRuleService ruleService;

    @Autowired
    private CodePartService partService;

    @Test
    public void test() {
        Long ruleId = 233L;
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


    @Test
    public void test4(){
        String s = " ";
        String s2 = "";
        String s3 = "qwe";
        System.out.println(StringUtils.isBlank(s));
        System.out.println(StringUtils.isBlank(s2));
        System.out.println(StringUtils.isBlank(s3));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入年：");
        int year = sc.nextInt();
        System.out.println("请输入月份：");
        int month = sc.nextInt();
        //1.计算1900.1.1到输入年的天数
        int dayOfYear = 0;
        for (int i = 1900; i < year; i++){
            if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0){
                dayOfYear += 366;
            }else {
                dayOfYear += 365;
            }
        }
        //2.计算1月到输入月的天数
        int dayOfMonth = 0;
        for (int i = 1; i < month; i++){
            switch (i){
                case 1:case 3:case 5:case 7:case 8:case 10:case 12:
                    dayOfMonth += 31;
                    break;
                case 4:case 6:case 9:case 11:
                    dayOfMonth += 30;
                    break;
                case 2:
                    if((year%4==0)&&(year%100!=0)||(year%400==0)){
                        dayOfMonth += 29;
                    }
                    else{
                        dayOfMonth += 28;
                    }
                    break;
            }
        }
        //3.获取输入月的天数
        int day = 0;
        switch (month){
            case 1:case 3:case 5:case 7:case 8:case 10:case 12:
                day = 31;
                break;
            case 4:case 6:case 9:case 11:
                day = 30;
                break;
            case 2:
                if((year%4==0)&&(year%100!=0)||(year%400==0)){
                    day = 29;
                }
                else{
                    day = 28;
                }
                break;
        }
        //4.计算星期
        int allDay = dayOfYear + dayOfMonth + 1;
        int week = allDay % 7;
        int count = 0;//计数器,记录日期的空格

        System.out.println("星期日\t星期一\t星期二\t星期三\t星期四\t星期五\t星期六");
        //4.打印空格
        for(int i=1;i<= week;i++){
            System.out.print("\t\t");
            count++;
        }
        //6. 打印日历
        for(int i=1;i<=day;i++){
            System.out.print(i+"\t\t");
            count++;
            //若记录数是七的倍数，换行输出
            if(count % 7 == 0){
                System.out.println();
            }
        }

    }
}
