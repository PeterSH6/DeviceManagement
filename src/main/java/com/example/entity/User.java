package com.example.entity;

import lombok.Data;

import javax.persistence.*;

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
    private String passWord;

    @Column
    private Boolean accountNonExpired;

    @Column
    private Boolean accountNonLocked;

    @Column
    private String authorities;

    public User() {
    }

    public User(String userName, Integer userId, String passWord, Boolean accountNonExpired, Boolean accountNonLocked, String authorities) {
        this.userName = userName;
        this.userId = userId;
        this.passWord = passWord;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.authorities = authorities;
    }
}
