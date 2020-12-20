package com.example.modules.user.aspect;

import com.example.cache.DeviceCache;
import com.example.cache.UserCache;
import com.example.common.constant.DeviceStatus;
import com.example.common.constant.OrderStatus;
import com.example.common.exception.BusinessException;
import com.example.common.response.RespCode;
import com.example.dao.OrderRepository;
import com.example.entity.Device;
import com.example.entity.Order;
import com.example.modules.user.vo.UserRegisterVO;
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

        //if don't have this device
        if(deviceCache.getDevice(deviceId) == null) {
            throw new BusinessException(RespCode.ERR_GET_DEVICE);
        }

        Integer userId = userCache.getUser(SecurityContextHolder.getContext().getAuthentication().getName()).getUserId();
        //cannot redundantly apply one device
        List<Order> orders = orderRepository.findByOrderStatus(OrderStatus.ENQUEUE.getCode());
        for(Order order : orders) {
            if(order.getDevice().getDeviceId().equals(deviceId)) {
                throw new BusinessException(RespCode.ERR_GET_DEVICE);
            }
        }

        Device device = deviceCache.getDevice(deviceId);

        //该设备第一次被申请，需要转换为Reserved状态。该状态下设备仍可被申请
        if(device.getDeviceStatus().equals(DeviceStatus.FREE.getCode())) {
            device.setDeviceStatus(DeviceStatus.RESERVED.getCode());
            deviceCache.putDevice(device);
        }
        //can not apply broken device
        //当设备状态为occupied的时候，用户可以创建订单，但是teacher处无法处理，因为没有设备
        if(deviceCache.getDevice(deviceId).getDeviceStatus().equals(DeviceStatus.BROKEN.getCode())) {
            throw new BusinessException(RespCode.ERR_DEVICE_STATUS);
        }

    }

    @Before("com.example.modules.user.aspect.UserAspect.userRegisterPC(com.example.modules.user.vo.UserRegisterVO) && args(userRegisterVO)")
    public void beforeUserRegisterService(UserRegisterVO userRegisterVO) {
        if(userCache.getUser(userRegisterVO.getUserName()) != null) {
            throw new BusinessException(RespCode.ERR_REGISTER);
        }
        if(userRegisterVO.getAuthorities() == null || userRegisterVO.getAuthorities() == "") {
            userRegisterVO.setAuthorities("ROLE_STUDENT");
        }
    }


}
