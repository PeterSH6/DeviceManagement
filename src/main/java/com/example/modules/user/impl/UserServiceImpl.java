package com.example.modules.user.impl;


import com.example.dao.OrderRepository;
import com.example.dao.UserRepository;
import com.example.modules.user.service.UserService;
import com.example.modules.user.vo.DeviceOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void applyOneDevice(Integer deviceId) {

    }

    @Override
    public void returnOneDevice(Integer deviceId) {

    }

    @Override
    public List<DeviceOrderVO> getAllMyDeviceOrders() {
        return null;
    }

    @Override
    public List<DeviceOrderVO> getAllMySuccessDeviceOrders() {
        return null;
    }

    @Override
    public List<DeviceOrderVO> getAllMyFailedDeviceOrders() {
        return null;
    }

    @Override
    public List<DeviceOrderVO> getAllMyEnqueueDeviceOrders() {
        return null;
    }
}
