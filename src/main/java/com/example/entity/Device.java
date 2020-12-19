package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "tb_device")
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id",referencedColumnName = "userId")
    private User user;

    //device是1端
    @OneToMany(mappedBy = "device",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Order> orders;

    @OneToOne(mappedBy = "device")
    private Broken broken;

    @Column
    private Integer deviceStatus;

    public Device() {}

    public Device(Integer deviceId, String deviceName, String deviceType, String devicePara, Date buyTime, String devicePrice, String deviceManufacture, Date warranty, User user, Integer deviceState) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.devicePara = devicePara;
        this.buyTime = buyTime;
        this.devicePrice = devicePrice;
        this.deviceManufacture = deviceManufacture;
        this.warranty = warranty;
        this.user = user;
        this.deviceStatus = deviceState;
    }

}
