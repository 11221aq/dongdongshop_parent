package com.dongdongshop.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dongdongshop.model.Operation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.util.PageParms;
import com.dongdongshop.vo.OperationVO;

/**
* @author 29555
* @description 针对表【operation(工序表)】的数据库操作Service
* @createDate 2023-03-20 16:13:38
*/
public interface OperationService extends IService<Operation> {

    Page<OperationVO> getOperationList(PageParms<OperationVO> parms);

    void addOperation(OperationVO vo);

    OperationVO getOperationById(String oid);

    void updateOperation(OperationVO vo);

    void deleteOperation(String oid);
}
