package com.example.entity;

import com.example.constant.BrokenType;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Broken")
public class Broken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recordId;

//    @Column
//    private Integer deviceId;

    @Column
    private Date brokeTime;

    @Column
    private String reason;

    @Column
    private BrokenType brokenType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deviceId",referencedColumnName = "deviceId")
    private Device device;

    public Broken() {
    }

    public Broken(Integer recordId, Date brokeTime, String reason, BrokenType brokenType, Device device) {
        this.recordId = recordId;
        this.brokeTime = brokeTime;
        this.reason = reason;
        this.brokenType = brokenType;
        this.device = device;
    }
}
