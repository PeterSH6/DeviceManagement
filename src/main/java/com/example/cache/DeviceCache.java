package com.example.cache;

import com.example.dao.DeviceRepository;
import com.example.entity.Device;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@CacheConfig(cacheNames = "device")
@Slf4j
public class DeviceCache {

    @Autowired
    private DeviceRepository deviceRepository;

    @Cacheable(key = "#deviceId")
    public Device getDevice(Integer deviceId) {
        log.info("Caching Device with <id> : <" + deviceId + ">...");
        return deviceRepository.findByDeviceId(deviceId);
    }

    @CachePut(key = "#device.deviceId")
    public Device putDevice(Device device) {
        log.info("Writing through Device cache with <deviceId>: <" + device.getDeviceId() + ">...");
        deviceRepository.save(device);
        return device;
    }

    @CacheEvict(key = "#deviceId")
    public void evictDevice(Integer deviceId) {
        log.info("Evicting Device Cache with <deviceId>: <" + deviceId + ">...");
        deviceRepository.deleteByDeviceId(deviceId);
    }
}
