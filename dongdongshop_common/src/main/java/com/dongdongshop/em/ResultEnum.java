package com.dongdongshop.em;

public enum ResultEnum {
    SUCCESS(10000, "操作成功"),
    ERROR(10001, "操作失败"),
    NOLOGIN(10002, "未登录"),
    ARGS_ERROR(10003, "参数错误"),
    LOGIN_ERROR(10004, "密码或用户名错误"),
    NOT_LOGIN(10005, "用户未登录"),
    INVALID_TOKEN(10006, "无效的Token"),
    TO_BE_REVIEWED(10007, "账户待审核"),
    ;

    private Integer code;
    private String message;

    ResultEnum() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
