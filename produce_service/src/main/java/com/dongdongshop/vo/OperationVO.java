package com.dongdongshop.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
public class OperationVO implements Serializable {
    private String oid;

    private String oname;

    private Integer status;

    private String desc;
}