package com.example.modules.user.impl;

import com.example.cache.DeviceCache;
import com.example.cache.UserCache;
import com.example.common.constant.DeviceStatus;
import com.example.common.constant.OrderStatus;
import com.example.dao.OrderRepository;
import com.example.entity.Order;
import com.example.entity.User;
import com.example.modules.user.service.SearchOrderService;
import com.example.modules.user.vo.DeviceOrderVO;
import com.example.modules.user.vo.VOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchOrderServiceImpl implements SearchOrderService {

    @Autowired
    private UserCache userCache;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DeviceCache deviceCache;

    @Autowired
    private VOUtils voUtils;

    @Override
    public List<DeviceOrderVO> getAllMyDeviceOrders() {
        User user = userCache.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Order> orders = orderRepository.findByUser(user);
        return voUtils.deviceOrderTODeviceOrderVOS(orders);
    }

    @Override
    public List<DeviceOrderVO> getAllMySuccessDeviceOrders() {
        User user = userCache.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Order> orders = orderRepository.findByUserAndOrderStatus(user,OrderStatus.FINISH_SUCCESS.getCode());
        return voUtils.deviceOrderTODeviceOrderVOS(orders);
    }

    @Override
    public List<DeviceOrderVO> getAllMyFailedDeviceOrders() {
        User user = userCache.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Order> orders = orderRepository.findByUserAndOrderStatus(user,OrderStatus.FINISH_FAILED.getCode());
        return voUtils.deviceOrderTODeviceOrderVOS(orders);
    }

    @Override
    public List<DeviceOrderVO> getAllMyEnqueueDeviceOrders() {
        User user = userCache.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Order> orders = orderRepository.findByUserAndOrderStatus(user, OrderStatus.ENQUEUE.getCode());
        return voUtils.deviceOrderTODeviceOrderVOS(orders);
    }

    @Override
    public List<DeviceOrderVO> getAllMyEnqueueNotOccupiedDeviceOrders() {
        User user = userCache.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Order> orders = orderRepository.findByUserAndOrderStatus(user,OrderStatus.ENQUEUE.getCode());
        List<Order> orders1 = new ArrayList<>();
        //not occupied or not broken
        for(Order order : orders) {
            if(order.getDevice().getDeviceStatus().equals(DeviceStatus.FREE.getCode()) ||
                    order.getDevice().getDeviceStatus().equals(DeviceStatus.RESERVED.getCode())) {
                orders1.add(order);
            }
        }
        return voUtils.deviceOrderTODeviceOrderVOS(orders1);
    }
}
