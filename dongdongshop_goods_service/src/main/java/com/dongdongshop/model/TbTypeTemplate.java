package com.dongdongshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbTypeTemplate {
    private Long id;

    private String name;

    private String specIds;

    private String brandIds;

    private String customAttributeItems;
}