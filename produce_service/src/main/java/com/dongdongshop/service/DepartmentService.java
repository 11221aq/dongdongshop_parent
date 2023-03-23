package com.dongdongshop.service;

import com.dongdongshop.model.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.vo.DepartmentVO;

import java.util.List;

/**
* @author 29555
* @description 针对表【department(部门表)】的数据库操作Service
* @createDate 2023-03-21 21:09:05
*/
public interface DepartmentService extends IService<Department> {

    List<DepartmentVO> getDepartmentList(DepartmentVO vo);

    void getDepartment(DepartmentVO vo);

    void addDepartment(DepartmentVO vo);

    DepartmentVO getDepartmentById(Integer id);

    void deleteById(Integer id);
}
