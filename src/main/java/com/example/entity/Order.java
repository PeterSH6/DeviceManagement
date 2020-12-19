package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId",referencedColumnName = "userId")
    private User user;

    //单向1-1
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deviceId",referencedColumnName = "deviceId")
    private Device device;

    @Column
    private Integer orderStatus;

    @Column
    private Date createdTime;

    @Column
    private Date finishedTime;

    @Column
    private Date returnTime;

    //TODO : ischaoshi, isbroken
    //TODO : 查询order写个aop检查doing并修改状态(ischaoshi,isbroken,orderstatus)

    public Order() {
    }

    public Order(Integer orderId, User user, Device device, Integer orderStatus, Date createdTime, Date finishedTime, Date returnTime) {
        this.orderId = orderId;
        this.user = user;
        this.device = device;
        this.orderStatus = orderStatus;
        this.createdTime = createdTime;
        this.finishedTime = finishedTime;
        this.returnTime = returnTime;
    }
}
