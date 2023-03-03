package com.dongdongshop.vo;

import lombok.Data;

@Data
public class AlipayTradeRefundVO {
    private String code;
    private String msg;
    private String trade_no;
    private String out_trade_no;
    private String open_id;
    private String buyer_logon_id;
    private String fund_change;
    private String refund_fee;
    private String refund_currency;
    private String gmt_refund_pay;
}
