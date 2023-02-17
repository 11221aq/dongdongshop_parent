package com.dongdongshop.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbSpecificationOptionVO {
    private Long id;

    private String optionName;

    private Long specId;

    private Integer orders;

}