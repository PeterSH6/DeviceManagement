package com.example.modules.device.vo;

import com.example.entity.Broken;
import com.example.entity.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * use to add a device for a teacher
 */
@Data
public class DeviceAddVO {

    @NotBlank
    private String deviceName;

    @NotBlank
    private String deviceType;

    @NotBlank
    private String devicePara;

    @NotBlank
    private String devicePrice;

    @NotBlank
    private String deviceManufacture;

    @NotBlank
    private Date warranty; //保修期

}
