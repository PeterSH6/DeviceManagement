package com.example.common.response;

import lombok.Getter;

public enum RespCode {

    // 自定义状态码从1000开始, OK开头表示成功，ERR开头表示失败，AD开头表示访问拒绝（没有权限）
    // OK从1000开始，ERR从2000，AD从3000
    // 此处只是示例，并非所有操作都要有状态码

    OK(200, "ok"),
    ERR(500, "error"),
    AD(403, "access denied"),

    OK_LOGIN(1000, "login ok"),
    OK_GET_BOOKS(1001, "get device ok"),
    OK_REGISTER(1002, "register ok"),
    OK_LOGOUT(1003,"logout ok"),

    ERR_LOGIN(2000, "login error"),
    ERR_GET_DEVICE(2001, "get device error"),
    ERR_REMOVE_DEVICE(2002, "remove device error"),
    ERR_UPDATE_DEVICE(2003, "update device error"),
    ERR_DEVICE_STATUS(2004, "device status error"),
    ERR_CONFIRM_ORDER(2005, "confirm order error"),  // 不能确认不存在的订单

    AD_REMOVE_DEVICE(3000, "remove device access denied"),
    AD_UPDATE_DEVICE(3001, "update device access denied"),
    AD_ADD_DEVICE(3002, "update device access denied"),
    AD_GET(3003, "get device access denied")  // 同一用户不能在订单结束前重复购买同一设备
    ;

    @Getter
    private Integer code;

    @Getter
    private String msg;

    RespCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
