package com.example.modules.user.vo;

import com.example.entity.Broken;
import com.example.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class DeviceVO {
    private Integer deviceId;

    private String deviceName;

    private String deviceType;

    private Date warranty; //保修期

    private User user;

    private Broken broken;

    private Integer deviceStatus;
}
