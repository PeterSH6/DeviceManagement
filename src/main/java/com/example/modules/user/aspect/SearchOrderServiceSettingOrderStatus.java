package com.example.modules.user.aspect;

import com.example.common.constant.DeviceStatus;
import com.example.common.constant.OrderStatus;
import com.example.dao.OrderRepository;
import com.example.entity.Device;
import com.example.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Aspect
@Component
@Slf4j
public class SearchOrderServiceSettingOrderStatus {

    @Autowired
    private OrderRepository orderRepository;

    //Only Set DOING Status orders
    @After(value = "com.example.modules.user.aspect.UserAspect.searchOrderServicePC()")
    public void afterSearchOrderServiceToSetOrderStatus() {
        log.info("Setting the orderStatus by comparing the return time or broken status");
        List<Order> orders = orderRepository.findByOrderStatus(OrderStatus.DOING.getCode());
        for(Order order : orders) {
            Device device = order.getDevice();
            if(device.getDeviceStatus().equals(DeviceStatus.BROKEN.getCode())) {
                order.setOrderStatus(OrderStatus.FINISH_BROKEN.getCode());
                order.setIsBroken(true);
                orderRepository.save(order);
            }
            Date date = new Date();
            if(date.getTime() >= order.getReturnTime().getTime()) {
                order.setIsLate(true);
                //不需要设置OrderStatus，因为超时并不会影响status
                orderRepository.save(order);
            }
        }

    }
}
