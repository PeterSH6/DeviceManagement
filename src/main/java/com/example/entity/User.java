package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tb_user")
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

    //user为1端
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Order> orders;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Device> device;

    public User() {
    }

    public User(String userName, Integer userId, String authorities, String passWord, Boolean accountNonExpired, Boolean accountNonLocked, Boolean credentialNonExpired, Boolean enabled, List<Order> orders, List<Device> device) {
        this.userName = userName;
        this.userId = userId;
        this.authorities = authorities;
        this.passWord = passWord;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialNonExpired = credentialNonExpired;
        this.enabled = enabled;
        this.orders = orders;
        this.device = device;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                '}';
    }
}
