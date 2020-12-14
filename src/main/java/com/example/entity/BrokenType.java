package com.example.entity;

import lombok.Getter;

public enum BrokenType {

    BrokenDown(100,"Cannot Be Fixed"),

    FixAble(200,"Can Be Fixed"),

    NeedReplace(300,"Need to Replace some parts");


    @Getter
    private Integer Type;

    @Getter
    private String Msg;

    BrokenType(Integer Type, String msg)
    {
        this.Type = Type;
        this.Msg = msg;
    }
}
