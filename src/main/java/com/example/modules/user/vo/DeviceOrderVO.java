package com.example.modules.user.vo;

import com.example.modules.device.vo.DeviceVO;
import lombok.Data;

import java.util.Date;

@Data
public class DeviceOrderVO {

    private Date createdTime;

    private Date finishedTime;

    private Date returnTime;

    private Integer orderStatus;

    private DeviceVO device;

    private UserInfoVO user;

    private UserInfoVO teacher;

    private String notes;
}
