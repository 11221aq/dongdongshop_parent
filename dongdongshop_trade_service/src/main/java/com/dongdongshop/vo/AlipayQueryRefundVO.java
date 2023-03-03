package com.dongdongshop.vo;

import lombok.Data;

@Data
public class AlipayQueryRefundVO {
    private String code;
    private String msg;
    private String trade_no;
    private String out_trade_no;
    private String out_request_no;
    private String refund_reason;
    private String total_amount;
    private String refund_amount;
    private String industry_sepc_detail;
    private String refund_status;
    private String error_code;
}
