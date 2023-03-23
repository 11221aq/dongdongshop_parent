package com.dongdongshop.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.dongdongshop.model.Scheduling;
import lombok.Data;

/**
 * @TableName  produce
 */
@Data
public class ProduceVO implements Serializable {
    private String pid;

    private String pname;

    private Integer psid;

    private String porder;

    private String productOrder;

    private String productName;

    private String spec;

    private String unit;

    private Long orderNum;

    private Long adjustQuantity;

    private Long quantitProduced;

    private Long batchNum;

    private String customerCode;

    private String customerName;

    private Date needTime;

    private Integer status;

    private String parentid;

    private List<ProduceVO> voList;

    private List<Scheduling> schedulingList;

}