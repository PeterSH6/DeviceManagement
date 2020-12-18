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
        Device device = deviceCache.getDevice(deviceId);
        //TODO:如果此处进行了设置，那么只允许有一个人申请
        device.setDeviceStatus(DeviceStatus.RESERVED.getCode());
        deviceCache.putDevice(device);
        Order order = new Order();
        order.setCreatedTime(new Date());
        order.setDevice(device);
        User user = userCache.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        order.setUser(user);
        order.setOrderStatus(OrderStatus.ENQUEUE.getCode());
        orderRepository.save(order);
    }

    @Override
    public void returnOneDevice(Integer deviceId) {
        Device device = deviceCache.getDevice(deviceId);
        //TODO:此处设计可能有问题 归还device直接将device的状态改为free,user改为null
        device.setUser(null);
        device.setDeviceStatus(DeviceStatus.FREE.getCode());
    }

    @Override
    public List<DeviceOrderVO> getAllMyDeviceOrders() {
        User user = userCache.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Order> orders = orderRepository.findByUser(user);
        return voUtils.deviceOrderTODeviceOrderVOS(orders);
    }

    @Override
    public List<DeviceOrderVO> getAllMySuccessDeviceOrders() {
        User user = userCache.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Order> orders = orderRepository.findByUserAndOrderStatus(user,OrderStatus.SUCCESS.getCode());
        return voUtils.deviceOrderTODeviceOrderVOS(orders);
    }

    @Override
    public List<DeviceOrderVO> getAllMyFailedDeviceOrders() {
        User user = userCache.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Order> orders = orderRepository.findByUserAndOrderStatus(user,OrderStatus.FAILED.getCode());
        return voUtils.deviceOrderTODeviceOrderVOS(orders);
    }

    @Override
    public List<DeviceOrderVO> getAllMyEnqueueDeviceOrders() {
        User user = userCache.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Order> orders = orderRepository.findByUserAndOrderStatus(user,OrderStatus.ENQUEUE.getCode());
        return voUtils.deviceOrderTODeviceOrderVOS(orders);
    }
}
