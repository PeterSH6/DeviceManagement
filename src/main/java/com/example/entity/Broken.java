package com.example.entity;

import com.example.constant.BrokenType;
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

    @Column
    private Integer deviceId;

    @Column
    private Date brokeTime;

    @Column
    private String reason;

    @Column
    private BrokenType brokenType;

    public Broken() {
    }

    public Broken(Integer recordId, Integer deviceId, Date brokeTime, String reason, BrokenType brokenType) {
        this.recordId = recordId;
        this.deviceId = deviceId;
        this.brokeTime = brokeTime;
        this.reason = reason;
        this.brokenType = brokenType;
    }
}
