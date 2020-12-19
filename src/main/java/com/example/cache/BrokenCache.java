package com.example.cache;

import com.example.dao.BrokenRepository;
import com.example.entity.Broken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @author https://github.com/anlowee
 */
@Component
@CacheConfig(cacheNames = "broken")
@Slf4j
public class BrokenCache {

    @Autowired
    private BrokenRepository brokenRepository;

    @Cacheable(key = "#deviceId")
    public Broken get(Integer deviceId) {
        return brokenRepository.findByDevice_DeviceId(deviceId);
    }

    @CachePut(key = "#broken.device.deviceId")
    public Broken put(Broken broken) {
        brokenRepository.save(broken);
        return broken;
    }

    @CacheEvict(key = "#deviceId")
    public void evict(Integer deviceId) {
        brokenRepository.deleteByDevice_DeviceId((deviceId));
    }

}
