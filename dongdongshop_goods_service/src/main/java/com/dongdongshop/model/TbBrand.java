package com.dongdongshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbBrand {
    private Long id;

    private String name;

    private String firstChar;

}