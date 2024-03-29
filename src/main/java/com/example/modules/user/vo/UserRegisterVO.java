package com.example.modules.user.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用户注册Visual Object
 */
@Data
public class UserRegisterVO {

    @NotBlank
    private String userName;

    @NotBlank
    private String passWord;

    @NotBlank
    private String authorities;

}
