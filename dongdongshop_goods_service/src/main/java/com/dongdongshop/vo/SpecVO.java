package com.dongdongshop.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpecVO {
    private String specName;

    private String spec;
    private Long id;
    private String optionList;

    private TbSpecificationVO specification;
    private List<TbSpecificationOptionVO> list;

}
