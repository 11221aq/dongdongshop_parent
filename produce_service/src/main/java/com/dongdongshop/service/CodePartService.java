package com.dongdongshop.service;

import com.dongdongshop.model.CodePart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.vo.CodePartVO;

import java.util.List;

/**
* @author 29555
* @description 针对表【sys_auto_code_part(编码生成规则组成表)】的数据库操作Service
* @createDate 2023-03-18 10:47:57
*/
public interface CodePartService extends IService<CodePart> {

    List<CodePartVO> getgetCodePartList(CodePartVO vo);

    void addCodePart(CodePartVO vo);

    CodePartVO getCodePartById(Long partId);

    void updateCodePart(CodePartVO vo);

    void deleteCodePartById(Long partId);
}
