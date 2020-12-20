package com.example.modules.device.vo;

import com.example.entity.Broken;
import com.example.entity.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * use to update a device
 */
@Data
public class DeviceUpdateVO {

    @NotBlank
    private Integer deviceId;

    private String deviceName;

    private String deviceType;

    private String devicePara;

    private String devicePrice;

    private String deviceManufacture;

    private Date warranty; //保修期

}
