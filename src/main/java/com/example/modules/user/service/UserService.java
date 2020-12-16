package com.example.modules.user.service;

import com.example.entity.Order;
import com.example.modules.user.vo.DeviceOrderVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    /**
     * apply for a device and create an order in the table
     * @param deviceId
     */
    void applyOneDevice(Integer deviceId);

    void returnOneDevice(Integer deviceId);

    List<DeviceOrderVO> getAllMyDeviceOrders();

    List<DeviceOrderVO> getAllMySuccessDeviceOrders();

    List<DeviceOrderVO> getAllMyFailedDeviceOrders();

    List<DeviceOrderVO> getAllMyEnqueueDeviceOrders();
}
