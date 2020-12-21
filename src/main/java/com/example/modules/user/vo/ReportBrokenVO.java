package com.example.modules.user.vo;

import lombok.Data;

@Data
public class ReportBrokenVO {

    private Integer deviceId;

    private String reason;

    private Integer brokenType;

}
