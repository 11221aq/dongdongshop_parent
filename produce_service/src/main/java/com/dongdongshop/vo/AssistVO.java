package com.dongdongshop.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
public class AssistVO implements Serializable {
    private Integer aid;

    private Integer asort;

    private String aexplain;

    private String aequipment;

    private String amaterial;

    private String adesc;

    private String pid;
}