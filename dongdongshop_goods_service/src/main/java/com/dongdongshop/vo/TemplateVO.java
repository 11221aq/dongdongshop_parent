package com.dongdongshop.vo;

import lombok.Data;

import java.util.List;
@Data
public class TemplateVO {
    private TbTypeTemplateVO typeTemplateVO;

    private List<SpecVO> specVOList;

}
