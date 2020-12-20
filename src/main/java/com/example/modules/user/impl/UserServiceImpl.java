package com.example.modules.user.impl;


import com.example.cache.DeviceCache;
import com.example.cache.UserCache;
import com.example.common.constant.DeviceStatus;
import com.example.common.constant.OrderStatus;
import com.example.dao.OrderRepository;
import com.example.dao.UserRepository;
import com.example.entity.Device;
import com.example.entity.Order;
import com.example.entity.User;
import com.example.modules.user.service.UserService;
import com.example.modules.user.vo.DeviceOrderVO;
import com.example.modules.user.vo.VOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserCache userCache;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DeviceCache deviceCache;

    @Autowired
    private VOUtils voUtils;

    @Override
    public void applyOneDevice(Integer deviceId) {
        Order order = new Order();
        order.setCreatedTime(new Date());
        order.setOrderStatus(OrderStatus.ENQUEUE.getCode());
        order.setDevice(deviceCache.getDevice(deviceId));
        order.setUser(userCache.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
        orderRepository.save(order);
    }

    @Override
    public void returnOneDevice(Integer deviceId) {
        Device device = deviceCache.getDevice(deviceId);
        device.setDeviceStatus(DeviceStatus.FREE.getCode());
        deviceCache.putDevice(device);
        Order order = orderRepository.findByUserAndDeviceAndOrderStatus(userCache.getUser(orderRepository.findByDevice_DeviceIdAndOrderStatus(deviceId, OrderStatus.DOING.getCode()).getUser().getUserId()),device,OrderStatus.DOING.getCode());
        order.setOrderStatus(OrderStatus.FINISH_SUCCESS.getCode());
        orderRepository.save(order);
    }

}
