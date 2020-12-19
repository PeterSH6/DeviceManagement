package com.example.common.constant;

import lombok.Getter;

public enum OrderStatus {

    ENQUEUE(0),
    DOING(1),
    FINISH_SUCCESS(2),
    FINISH_FAILED(3),
    FINISH_BROKEN(4);

    @Getter
    private Integer code;

    OrderStatus(Integer code) {this.code =code ;}
}
