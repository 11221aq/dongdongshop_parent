package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.model.Process;
import com.dongdongshop.service.ProcessService;
import com.dongdongshop.mapper.ProcessMapper;
import com.dongdongshop.service.TecproService;
import com.dongdongshop.vo.ProcessVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author 29555
* @description 针对表【process(工艺组成)】的数据库操作Service实现
* @createDate 2023-03-20 14:38:05
*/
@Service
public class ProcessServiceImpl extends ServiceImpl<ProcessMapper, Process>
    implements ProcessService{

    @Resource
    private TecproService service;

    @Resource
    private ProcessMapper mapper;

    @Override
    public List<ProcessVO> getProcessList(Long tid) {
        List<Long> list = service.getProcessList(tid);
        List<ProcessVO> collect = mapper.selectBatchIds(list).stream().map(p -> {
            ProcessVO vo = new ProcessVO();
            BeanUtils.copyProperties(p, vo);
            return vo;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    @Transactional
    public void addProcess(ProcessVO vo) {
        Process process = new Process();
        BeanUtils.copyProperties(vo, process);
        mapper.insert(process);
        service.addContent(process);
    }

    @Override
    public ProcessVO getPrcessById(Long pid) {
        Process p = mapper.selectById(pid);
        ProcessVO vo = new ProcessVO();
        BeanUtils.copyProperties(p, vo);
        return vo;
    }

    @Override
    public void updateProcess(ProcessVO vo) {
        Process process = new Process();
        BeanUtils.copyProperties(vo, process);
        mapper.updateById(process);
    }

    @Override
    @Transactional
    public void deleteProcess(Long pid) {
        mapper.deleteById(pid);
        service.deleteByPid(pid);
    }
}




