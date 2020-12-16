package com.example.common.constant;

import lombok.Getter;

public enum DeviceStatus {

    FREE(0),
    RESERVED(1),
    OCCUPIED(2),
    BROKEN(3);

    @Getter
    private Integer code;

    DeviceStatus(Integer code) { this.code = code ;}
}
