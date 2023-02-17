package com.dongdongshop.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbTypeTemplateVO {
    private Long id;

    private String name;

    private String specIds;

    private String brandIds;

    private String customAttributeItems;
}