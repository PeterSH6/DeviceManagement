package com.example.modules.repair.impl;

import com.example.cache.BrokenCache;
import com.example.cache.DeviceCache;
import com.example.common.constant.BrokenType;
import com.example.common.constant.DeviceStatus;
import com.example.dao.BrokenRepository;
import com.example.entity.Broken;
import com.example.entity.Device;
import com.example.modules.repair.service.RepairService;
import com.example.modules.repair.vo.BrokenVO;
import com.example.modules.user.vo.VOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author https://github.com/anlowee
 */
@Service
public class RepairServiceImpl implements RepairService {

    @Autowired
    private BrokenCache brokenCache;

    @Autowired
    private DeviceCache deviceCache;

    @Autowired
    private BrokenRepository brokenRepository;

    @Autowired
    private VOUtils voUtils;

    @Override
    public List<BrokenVO> getAllBroken() {
        return voUtils.brokenToBrokenVO(brokenRepository.findAll());
    }

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
