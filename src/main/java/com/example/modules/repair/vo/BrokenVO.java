package com.example.modules.repair.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author https://github.com/anlowee
 */
@Data
public class BrokenVO {

    private Integer recordId;

    private Integer deviceId;

    private Date brokeTime;

    private String reason;

    private Integer brokenType;

}
