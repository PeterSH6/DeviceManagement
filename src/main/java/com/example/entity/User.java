package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "User")
public class User {

    @Column
    private String userName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column
    private String authorities;

    @Column
    private String passWord;

    @Column
    private Boolean accountNonExpired;

    @Column
    private Boolean accountNonLocked;

    @Column
    private Boolean credentialNonExpired;

    @Column
    private Boolean enabled;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private List<Order> orders;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "user")
    private Device device;

    public User() {
    }

    public User(String userName,Boolean credentialNonExpired, Boolean enabled, Integer userId, String authorities, String passWord, Boolean accountNonExpired, Boolean accountNonLocked, List<Order> orders, Device device) {
        this.userName = userName;
        this.userId = userId;
        this.authorities = authorities;
        this.passWord = passWord;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.orders = orders;
        this.device = device;
        this.enabled = enabled;
        this.credentialNonExpired = credentialNonExpired;
    }
}
