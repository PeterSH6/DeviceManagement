package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Broken")
public class Broken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String RecordId;

    private String DeviceId;

    private Date BrokeTime;

    private String Reason;

    private BrokenType BrokenType;

}
