package com.example.modules.user.service;

import com.example.modules.user.vo.DeviceOrderVO;

import java.util.List;

public interface SearchOrderService {
    List<DeviceOrderVO> getAllMyDeviceOrders();

    List<DeviceOrderVO> getAllMySuccessDeviceOrders();

    List<DeviceOrderVO> getAllMyFailedDeviceOrders();

    List<DeviceOrderVO> getAllMyEnqueueDeviceOrders();

    List<DeviceOrderVO> getAllMyEnqueueNotOccupiedDeviceOrders();

    List<DeviceOrderVO> getAllMyDoingDeviceOrders();
}
