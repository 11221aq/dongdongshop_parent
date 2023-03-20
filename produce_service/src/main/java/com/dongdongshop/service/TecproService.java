package com.dongdongshop.service;

import com.dongdongshop.model.Process;
import com.dongdongshop.model.Tecpro;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 29555
* @description 针对表【tecpro(工艺工序中间表)】的数据库操作Service
* @createDate 2023-03-20 14:40:40
*/
public interface TecproService extends IService<Tecpro> {

    List<Long> getProcessList(Long tid);

    void addContent(Process process);

    void deleteByPid(Long pid);

    void deleteTecpro(Long tid);
}
