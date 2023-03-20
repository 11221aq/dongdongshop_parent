package com.dongdongshop.service;

import com.dongdongshop.model.Tecct;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.vo.TecctVO;

import java.util.List;

/**
* @author 29555
* @description 针对表【tecct(工艺物料产品中间表)】的数据库操作Service
* @createDate 2023-03-20 17:00:39
*/
public interface TecctService extends IService<Tecct> {

    List<Long> getTecctList(Long pid);

    void addDate(TecctVO vo);

    void updateDate(TecctVO vo);

    void deleteDate(String productid);

    void deleteTecct(Long tid);
}
