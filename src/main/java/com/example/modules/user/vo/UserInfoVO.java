package com.example.modules.user.vo;

import com.example.entity.Device;
import com.example.entity.Order;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class UserInfoVO {

    private String userName;

    private Integer userId;

    private Integer totalDeviceNum;

    private Boolean isAdmin;

}
