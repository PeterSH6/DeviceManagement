package com.example.modules.device.vo;

import com.example.entity.Broken;
import com.example.entity.User;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class DeviceVO {

    private Integer deviceId;

    private String deviceName;

    private String deviceType;

    private String devicePara;

    private String devicePrice;

    private String deviceManufacture;

    private Date warranty; //保修期

    private Integer deviceStatus;


}
