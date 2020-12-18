package com.example.modules.user.aspect;

import com.example.cache.DeviceCache;
import com.example.cache.UserCache;
import com.example.common.constant.DeviceStatus;
import com.example.common.exception.BusinessException;
import com.example.common.response.RespCode;
import com.example.dao.OrderRepository;
import com.example.entity.Device;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Slf4j
@Order(1)
public class TeacherConfirmOrderValidation {

    @Autowired
    private DeviceCache deviceCache;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserCache userCache;

    @Before(value = "com.example.modules.user.aspect.UserAspect.teacherServiceConfirmOrderPC(Integer,Integer) && args(deviceId,userId)")
    public void beforeTeacherConfirmOrderPC(Integer deviceId, Integer userId) {
        log.info("Validate is confirm operation illegal before SellerService.confirmOrder()...");
        List<com.example.entity.Order> orders = orderRepository.findByUser(userCache.getUser(userId));
        if(orders.size() == 0) {
            throw new BusinessException(RespCode.ERR_CONFIRM_ORDER);
        }

        //if not free
        if (!deviceCache.getDevice(deviceId).getDeviceStatus().equals(DeviceStatus.FREE)) {
            throw new BusinessException(RespCode.ERR_DEVICE_STATUS);
        }


    }
}
