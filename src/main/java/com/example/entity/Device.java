package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "Device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer deviceId;

    @Column
    private String deviceName;

    @Column
    private String deviceType;

    @Column
    private String devicePara;

    @Column
    private Date buyTime;

    @Column
    private String devicePrice;

    @Column
    private String deviceManufacture;

    @Column
    private Date warranty; //保修期

    @Column
    private String userId;

    @Column
    private String teacherId;

    @Column
    private Integer deviceState;

    public Device() {}

    public Device(Integer deviceId, String deviceName, String deviceType, String devicePara, Date buyTime, String devicePrice, String deviceManufacture, Date warranty, String userId, String teacherId, Integer deviceState) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.devicePara = devicePara;
        this.buyTime = buyTime;
        this.devicePrice = devicePrice;
        this.deviceManufacture = deviceManufacture;
        this.warranty = warranty;
        this.userId = userId;
        this.teacherId = teacherId;
        this.deviceState = deviceState;
    }
}
