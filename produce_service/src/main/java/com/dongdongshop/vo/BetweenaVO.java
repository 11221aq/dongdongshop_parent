package com.dongdongshop.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
public class BetweenaVO implements Serializable {
    private Integer bid;

    private String pid;

    private String cid;
}