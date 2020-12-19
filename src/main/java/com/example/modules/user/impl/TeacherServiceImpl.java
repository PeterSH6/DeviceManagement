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
import com.example.modules.user.service.TeacherService;
import com.example.modules.user.vo.DeviceOrderVO;
import com.example.modules.device.vo.DeviceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private DeviceCache deviceCache;

    @Autowired
    private UserCache userCache;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void confirmOrder(Integer deviceId, Integer userId) {
        Device device = deviceCache.getDevice(deviceId);
        User user = userCache.getUser(userId);
        device.setDeviceStatus(DeviceStatus.OCCUPIED.getCode());
        deviceCache.putDevice(device);
        Order order = orderRepository.findByUserAndDeviceAndOrderStatus(user,device,OrderStatus.ENQUEUE.getCode());
        order.setOrderStatus(OrderStatus.DOING.getCode());
        //make and set return time
        Date current = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(current);
        calendar.add(Calendar.DATE,7); //7天之内归还
        current = calendar.getTime();
        order.setReturnTime(current);
        orderRepository.save(order);
    }

    @Override
    public void repairDevice(Integer deviceId) {

    }

}
