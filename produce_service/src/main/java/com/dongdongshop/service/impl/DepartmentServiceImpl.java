package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.model.Department;
import com.dongdongshop.service.DepartmentService;
import com.dongdongshop.mapper.DepartmentMapper;
import com.dongdongshop.vo.DepartmentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
* @author 29555
* @description 针对表【department(部门表)】的数据库操作Service实现
* @createDate 2023-03-21 21:09:05
*/
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department>
    implements DepartmentService{

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentVO> getDepartmentList(DepartmentVO vo) {
        List<DepartmentVO> collect = departmentMapper.selectList(new LambdaQueryWrapper<Department>()
                .eq(vo.getStatus() != null, Department::getStatus, vo.getStatus())
                .like(vo.getName() != null, Department::getName, vo.getName())
        ).stream().map(d -> {
            DepartmentVO v = new DepartmentVO();
            BeanUtils.copyProperties(d, v);
            return v;
        }).collect(Collectors.toList());
        getDeaprtMentJSON(collect,0);
        return collect;
    }

    private List<DepartmentVO> getDeaprtMentJSON(List<DepartmentVO> collect,Integer pid){
        List<DepartmentVO> list = new ArrayList<DepartmentVO>();
        for (DepartmentVO departmentVO : collect) {
            if(Objects.equals(departmentVO.getPid(),pid)){
                list.add(departmentVO);
                departmentVO.setVoList(getDeaprtMentJSON(collect,departmentVO.getId()));
            }
        }
        return list;
    }

    @Override
    public void getDepartment(DepartmentVO vo) {
        Department d = new Department();
        BeanUtils.copyProperties(vo,d);
        departmentMapper.updateById(d);
    }

    @Override
    public void addDepartment(DepartmentVO vo) {
        Department d = new Department();
        BeanUtils.copyProperties(vo,d);
        d.setCreatTime(new Date());
        departmentMapper.insert(d);
    }

    @Override
    public DepartmentVO getDepartmentById(Integer id) {
        Department department = departmentMapper.selectById(id);
        DepartmentVO v = new DepartmentVO();
        BeanUtils.copyProperties(department, v);
        return v;
    }

    @Override
    public void deleteById(Integer id) {
        departmentMapper.deleteById(id);
        List<Department> departments = departmentMapper.selectList(new LambdaUpdateWrapper<Department>()
                .eq(Department::getPid, id)
        );
        if(departments.size() > 0){//有值
            for (Department department : departments) {
                deleteById(department.getId());
            }
        }else {
            return;
        }
    }
}




