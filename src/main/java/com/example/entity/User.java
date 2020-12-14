package com.example.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "User")
public class User {

    private String userName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    private String passWord;

    private String phoneNum;

    private Boolean



}
