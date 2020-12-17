package com.example.modules.device.impl;

import com.example.cache.DeviceCache;
import com.example.cache.UserCache;
import com.example.common.constant.DeviceStatus;
import com.example.dao.DeviceRepository;
import com.example.entity.Broken;
import com.example.entity.Device;
import com.example.modules.device.service.DeviceManageService;
import com.example.modules.device.vo.DeviceAddVO;
import com.example.modules.device.vo.DeviceUpdateVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;

public class DeviceManageServiceImpl implements DeviceManageService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceCache deviceCache;

    @Autowired
    private UserCache userCache;

    @Override
    public void addDevice(DeviceAddVO deviceAddVO) {
        Device device = new Device();
        BeanUtils.copyProperties(deviceAddVO,device);
        device.setBuyTime(new Date()); //默认获取当前时间
        device.setUser(userCache.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
        device.setDeviceStatus(DeviceStatus.FREE.getCode());
        device.setUser(null); //??
        device.setBroken(null);
        deviceRepository.save(device);
    }

    @Override
    public void removeDevice(Integer deviceId) {
        deviceCache.evictDevice(deviceId);
    }

    @Override
    public void updateDevice(DeviceUpdateVO deviceUpdateVO) {
        Device device = deviceCache.getDevice(deviceUpdateVO.getDeviceId());
        BeanUtils.copyProperties(deviceUpdateVO,device);
        //TODO:Cache save or deviceRepo save
        deviceRepository.save(device);
    }
}
