package com.example.modules.user.service;

import com.example.entity.Order;
import com.example.modules.user.vo.DeviceOrderVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    /**
     * apply for a device and create an order in the table
     * @param deviceId
     */
    void applyOneDevice(Integer deviceId);

    @Transactional
    void returnOneDevice(Integer deviceId);

}
