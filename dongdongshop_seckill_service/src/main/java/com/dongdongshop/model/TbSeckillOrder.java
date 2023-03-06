package com.dongdongshop.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class TbSeckillOrder {
    private Long id;

    private Long seckillId;

    private BigDecimal money;

    private String userId;

    private String sellerId;

    private Date createTime;

    private Date payTime;

    private String status;

    private String receiverAddress;

    private String receiverMobile;

    private String receiver;

    private String transactionId;

}