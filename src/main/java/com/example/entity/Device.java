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
    private String DeviceId;

    private String DeviceName;

    private String DeviceType;

    private String DevicePara;

    private Date BuyTime;

    private String DevicePrice;

    private String DeviceManufacture;

    private Date Warranty; //保修期

    private String UserId;

    private String TeacherId;
}
