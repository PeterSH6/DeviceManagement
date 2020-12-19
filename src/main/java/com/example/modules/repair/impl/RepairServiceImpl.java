package com.example.modules.repair.impl;

import com.example.cache.BrokenCache;
import com.example.cache.DeviceCache;
import com.example.common.constant.BrokenType;
import com.example.common.constant.DeviceStatus;
import com.example.entity.Broken;
import com.example.entity.Device;
import com.example.modules.repair.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author https://github.com/anlowee
 */
@Service
public class RepairServiceImpl implements RepairService {

    @Autowired
    private BrokenCache brokenCache;

    @Autowired
    private DeviceCache deviceCache;

    @Override
    public Boolean repair(Integer deviceId) {
        Broken broken = brokenCache.get(deviceId);
        if (broken.getBrokenType().equals(BrokenType.FixAble.getType())) {
            brokenCache.evict(deviceId);
            Device device = deviceCache.getDevice(deviceId);
            device.setDeviceStatus(DeviceStatus.FREE.getCode());
            deviceCache.putDevice(device);
            return true;
        } else {
            brokenCache.evict(deviceId);
            return false;
        }
    }

}
