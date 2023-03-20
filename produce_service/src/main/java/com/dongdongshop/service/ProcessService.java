package com.dongdongshop.service;

import com.dongdongshop.model.Process;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.vo.ProcessVO;

import java.util.List;

/**
* @author 29555
* @description 针对表【process(工艺组成)】的数据库操作Service
* @createDate 2023-03-20 14:38:05
*/
public interface ProcessService extends IService<Process> {

    List<ProcessVO> getProcessList(Long tid);

    void addProcess(ProcessVO vo);

    ProcessVO getPrcessById(Long pid);

    void updateProcess(ProcessVO vo);

    void deleteProcess(Long pid);
}
