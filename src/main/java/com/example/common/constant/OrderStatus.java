package com.example.common.constant;

import lombok.Getter;

public enum OrderStatus {

    ENQUEUE(0),
    SUCCESS(1), //TODO: doing + finish
    FAILED(2);

    @Getter
    private Integer code;

    OrderStatus(Integer code) {this.code =code ;}
}
