package com.example.modules.user.aspect;

import com.example.cache.DeviceCache;
import com.example.cache.UserCache;
import com.example.common.constant.DeviceStatus;
import com.example.common.constant.OrderStatus;
import com.example.common.exception.BusinessException;
import com.example.common.response.RespCode;
import com.example.dao.OrderRepository;
import com.example.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Slf4j
public class UserServiceValidation {
    @Autowired
    private UserCache userCache;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DeviceCache deviceCache;

    @Before("com.example.modules.user.aspect.UserAspect.userServiceApplyOneDevicePC(Integer) && args(deviceId)")
    public void beforeUserServiceApplyOneDevicePC(Integer deviceId) {
        log.info("Validate is apply operation illegal before UserService.applyOneDevice()...");
        Integer userId = userCache.getUser(SecurityContextHolder.getContext().getAuthentication().getName()).getUserId();
        //cannot redundantly apply one device
        List<Order> orders = orderRepository.findByOrderStatusAndOrderStatus(OrderStatus.ENQUEUE.getCode(), OrderStatus.SUCCESS.getCode());
        for(Order order : orders) {
            if(order.getDevice().getDeviceId().equals(deviceId)) {
                throw new BusinessException(RespCode.ERR_GET_DEVICE);
            }
        }
        //can only apply a device which status is FREE
        if(!deviceCache.getDevice(deviceId).getDeviceStatus().equals(DeviceStatus.FREE)) {
            throw new BusinessException(RespCode.ERR_DEVICE_STATUS);
        }
    }


}
