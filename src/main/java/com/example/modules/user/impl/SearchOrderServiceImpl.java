package com.example.modules.user.impl;

import com.example.cache.DeviceCache;
import com.example.cache.UserCache;
import com.example.common.constant.DeviceStatus;
import com.example.common.constant.OrderStatus;
import com.example.dao.DeviceRepository;
import com.example.dao.OrderRepository;
import com.example.entity.Device;
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
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceCache deviceCache;

    @Autowired
    private VOUtils voUtils;

    @Override
    public List<DeviceOrderVO> getAllMyDeviceOrders() {
        User user = userCache.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Order> orders = new ArrayList<>();
        if(user.getAuthorities().equals("ROLE_TEACHER")) {
            List<Device> devices = deviceRepository.findByUser(user);
            for(Device device : devices) {
                List<Order> orders1 = orderRepository.findByDevice(device);
                orders.addAll(orders1);
            }
        }
        else if(user.getAuthorities().equals("ROLE_STUDENT")) {
            orders = orderRepository.findByUser(user);
        }
        return voUtils.deviceOrderTODeviceOrderVOS(orders);
    }

    @Override
    public List<DeviceOrderVO> getAllMySuccessDeviceOrders() {
        User user = userCache.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Order> orders = new ArrayList<>();
        if(user.getAuthorities().equals("ROLE_TEACHER")) {
            List<Device> devices = deviceRepository.findByUser(user);
            for(Device device : devices) {
                List<Order> orders1 = orderRepository.findByDeviceAndOrderStatus(device,OrderStatus.FINISH_SUCCESS.getCode());
                orders.addAll(orders1);
            }
        }
        else if(user.getAuthorities().equals("ROLE_STUDENT")) {
            orders = orderRepository.findByUserAndOrderStatus(user,OrderStatus.FINISH_SUCCESS.getCode());
        }

        return voUtils.deviceOrderTODeviceOrderVOS(orders);
    }

    @Override
    public List<DeviceOrderVO> getAllMyFailedDeviceOrders() {
        User user = userCache.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Order> orders = new ArrayList<>();
        if(user.getAuthorities().equals("ROLE_TEACHER")) {
            List<Device> devices = deviceRepository.findByUser(user);
            for(Device device : devices) {
                List<Order> orders1 = orderRepository.findByDeviceAndOrderStatus(device,OrderStatus.FINISH_FAILED.getCode());
                orders.addAll(orders1);
            }
        }
        else if(user.getAuthorities().equals("ROLE_STUDENT")) {
            orders = orderRepository.findByUserAndOrderStatus(user,OrderStatus.FINISH_FAILED.getCode());
        }
        return voUtils.deviceOrderTODeviceOrderVOS(orders);
    }

    @Override
    public List<DeviceOrderVO> getAllMyEnqueueDeviceOrders() {
        User user = userCache.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Order> orders = new ArrayList<>();
        if(user.getAuthorities().equals("ROLE_TEACHER")) {
            List<Device> devices = deviceRepository.findByUser(user);
            for(Device device : devices) {
                List<Order> orders1 = orderRepository.findByDeviceAndOrderStatus(device,OrderStatus.ENQUEUE.getCode());
                orders.addAll(orders1);
            }
        }
        else if(user.getAuthorities().equals("ROLE_STUDENT")) {
            orders = orderRepository.findByUserAndOrderStatus(user,OrderStatus.ENQUEUE.getCode());
        }
        return voUtils.deviceOrderTODeviceOrderVOS(orders);
    }

    //TODO:: ASPECT
    @Override
    public List<DeviceOrderVO> getAllMyEnqueueNotOccupiedDeviceOrders() {
        User user = userCache.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Order> orders = new ArrayList<>();
        if(user.getAuthorities().equals("ROLE_TEACHER")) {
            List<Device> devices = deviceRepository.findByUserAndDeviceStatusLessThanEqual(user,DeviceStatus.RESERVED.getCode());
            for(Device device : devices) {
                List<Order> orders1 = orderRepository.findByDeviceAndOrderStatus(device,OrderStatus.ENQUEUE.getCode());
                orders.addAll(orders1);
            }
        }
        else if(user.getAuthorities().equals("ROLE_STUDENT")) {
            orders = orderRepository.findByUserAndOrderStatus(user,OrderStatus.ENQUEUE.getCode());
        }

        return voUtils.deviceOrderTODeviceOrderVOS(orders);
    }
}
