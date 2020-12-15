package com.example.entity;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.omg.PortableInterceptor.INACTIVE;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Order")
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
    private Integer orderState;

    @Column
    private Date returnTime;

    public Order() {
    }

    public Order(Integer orderId, User user, Device device, Integer orderState, Date returnTime) {
        this.orderId = orderId;
        this.user = user;
        this.device = device;
        this.orderState = orderState;
        this.returnTime = returnTime;
    }
}
