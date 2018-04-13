package com.hik.order.enums;

public enum OrderStatusEnum {
    NEW(0,"新订单"),
    FINISHED(1,"已支付"),
    CANCEL(2,"取消订单")
    ;
    private Integer code;
    private String msg;


    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

