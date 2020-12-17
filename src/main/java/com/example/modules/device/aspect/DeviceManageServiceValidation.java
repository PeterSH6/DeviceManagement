package com.example.modules.device.aspect;

import com.example.cache.DeviceCache;
import com.example.cache.UserCache;
import com.example.common.exception.BusinessException;
import com.example.common.response.RespCode;
import com.example.entity.Device;
import com.example.entity.User;
import com.example.modules.device.vo.DeviceAddVO;
import com.example.modules.device.vo.DeviceUpdateVO;
import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class DeviceManageServiceValidation {

    @Autowired
    private DeviceCache deviceCache;

    @Autowired
    private UserCache userCache;

    @Before("com.example.modules.device.aspect.DeviceAspect.deviceManageServiceRemoveDevicePC(Integer) && args(deviceId)")
    public void beforeDeviceManageServiceRemoveDevicePC(Integer deviceId) {
        log.info("Validating the authority of removing device...");
        User user = userCache.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if(user.getAuthorities().contains("ROLE_ADMIN")) { //is teacher
            Device device = deviceCache.getDevice(deviceId);
            if(!device.getUser().getUserId().equals(user.getUserId())) {
                throw new BusinessException(RespCode.AD_REMOVE_DEVICE);
            }
        } else {
            //user cannot remove device
            throw new BusinessException(RespCode.AD_REMOVE_DEVICE);
        }
    }

    @Before("com.example.modules.device.aspect.DeviceAspect.deviceManageServiceAddDevicePC(com.example.modules.device.vo.DeviceAddVO) && args(deviceAddVO)")
    public void beforeDeviceManageServiceAddDevicePC(DeviceAddVO deviceAddVO) {
        log.info("Validating the authority of adding device...");
        User user = userCache.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user.getAuthorities().contains("ROLE_ADMIN")) {
            //user cannot add device
            throw new BusinessException(RespCode.AD_ADD_DEVICE);
        }
    }

    @Before("com.example.modules.device.aspect.DeviceAspect.deviceManageServiceUpdateDevicePC(com.example.modules.device.vo.DeviceUpdateVO) && args(deviceUpdateVO)")
    public void beforeDeviceManageServiceUpdateDevicePC(DeviceUpdateVO deviceUpdateVO) {
        log.info("Validating the authority of updating device...");
        User user = userCache.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user.getAuthorities().contains("ROLE_ADMIN")) { //is teacher
            if (!deviceCache.getDevice(deviceUpdateVO.getDeviceId()).getUser().getUserId().equals(user.getUserId())) {
                //the teacher doesn't have this device
                throw new BusinessException(RespCode.AD_UPDATE_DEVICE);
            }
        } else {
            //user cannot remove device
            throw new BusinessException(RespCode.AD_UPDATE_DEVICE);
        }
    }





}
