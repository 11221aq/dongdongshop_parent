package com.dongdongshop.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbItemCatVO {
    private Long id;

    private Long parentId;

    private String name;

    private Long typeId;

    private String typeName;

}