package com.dongdongshop.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Data
public class OrderVO {
    private BigDecimal payment;
    private String orderNum;//订单号
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date paymentTime;
    private String status;
    private String sellerId;
    private List<OrderPageVO> orderPageVOList;
}
