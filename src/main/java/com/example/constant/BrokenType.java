package com.example.constant;

import lombok.Getter;

public enum BrokenType {

    BrokenDown(100,"Cannot Be Fixed"),

    FixAble(200,"Can Be Fixed"),

    NeedReplace(300,"Need to Replace some parts");


    @Getter
    private Integer type;

    @Getter
    private String msg;

    BrokenType(Integer Type, String msg)
    {
        this.type = Type;
        this.msg = msg;
    }
}
