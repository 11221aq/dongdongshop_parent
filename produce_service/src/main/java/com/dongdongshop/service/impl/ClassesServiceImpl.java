package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.model.Classes;
import com.dongdongshop.model.SetType;
import com.dongdongshop.service.ClassesService;
import com.dongdongshop.mapper.ClassesMapper;
import com.dongdongshop.vo.SetTypeVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
* @author 29555
* @description 针对表【classes】的数据库操作Service实现
* @createDate 2023-03-23 18:27:48
*/
@Service
public class ClassesServiceImpl extends ServiceImpl<ClassesMapper, Classes>
    implements ClassesService{

    @Resource
    private ClassesMapper mapper;

    @Override
    public void insertDate(SetType s) {
        if(Objects.equals(s.getShiftMode(),"白班")){
            Classes c = new Classes();
            c.setSort(1);
            c.setName("白班");
            c.setStartTime("8:00");
            c.setEndTime("18:00");
            c.setSid(s.getId());
            mapper.insert(c);
        }
        if(Objects.equals(s.getShiftMode(),"两班倒")){
            Classes c = new Classes();
            c.setSort(1);
            c.setName("白班");
            c.setStartTime("8:00");
            c.setEndTime("20:00");
            c.setSid(s.getId());
            Classes c1 = new Classes();
            c1.setSort(2);
            c1.setName("夜班");
            c1.setEndTime("8:00");
            c1.setStartTime("20:00");
            c1.setSid(s.getId());
            mapper.insert(c);
            mapper.insert(c1);
        }
        if(Objects.equals(s.getShiftMode(),"三班倒")){
            Classes c = new Classes();
            c.setSort(1);
            c.setName("白班");
            c.setStartTime("8:00");
            c.setEndTime("16:00");
            c.setSid(s.getId());
            Classes c1 = new Classes();
            c1.setSort(2);
            c1.setName("中班");
            c1.setEndTime("24:00");
            c1.setStartTime("16:00");
            c1.setSid(s.getId());
            Classes c2 = new Classes();
            c2.setSort(2);
            c2.setName("夜班");
            c2.setEndTime("00:00");
            c2.setSid(s.getId());
            c2.setStartTime("08:00");
            mapper.insert(c);
            mapper.insert(c1);
            mapper.insert(c2);
        }
    }
}




