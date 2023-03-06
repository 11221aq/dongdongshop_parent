package com.dongdongshop.data;

import com.dongdongshop.em.ResultEnum;

public class Result<T> {
    private Integer code;
    private String message;
    private  T data;

    public static Result result(ResultEnum em){
        return new Result(em.getCode(),em.getMessage());
    }

    public static Result ok(){
        return new Result(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage());
    }

    public static Result error(){
        return new Result(ResultEnum.ERROR.getCode(),ResultEnum.ERROR.getMessage());
    }


    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result() {
    }

    public static Result fallback() {
        return new Result(ResultEnum.MORE_REQUEST.getCode(),ResultEnum.MORE_REQUEST.getMessage());
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

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }
}
