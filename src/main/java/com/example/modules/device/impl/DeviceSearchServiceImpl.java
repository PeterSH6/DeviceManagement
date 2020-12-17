package com.example.modules.device.impl;

import com.example.cache.DeviceCache;
import com.example.cache.UserCache;
import com.example.dao.DeviceRepository;
import com.example.dao.UserRepository;
import com.example.entity.Device;
import com.example.modules.device.service.DeviceSearchService;
import com.example.modules.device.vo.DeviceVO;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DeviceSearchServiceImpl implements DeviceSearchService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceCache deviceCache;

    @Autowired
    private UserCache userCache;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<DeviceVO> getAllDevice() {
        List<DeviceVO> deviceVOS = new ArrayList<>();
        List<Device> devices = deviceRepository.findAll();
        for (Device device : devices) {
            DeviceVO deviceVO = new DeviceVO();
            BeanUtils.copyProperties(device,deviceVO);
            deviceVOS.add(deviceVO);
        }
        return deviceVOS;
    }

    @Override
    public List<DeviceVO> getAllDevice(Example example) {
        List<DeviceVO> deviceVOS = new ArrayList<>();
        List<Device> devices = deviceRepository.findAll(example);
        for (Device device : devices) {
            DeviceVO deviceVO = new DeviceVO();
            BeanUtils.copyProperties(device,deviceVO);
            deviceVOS.add(deviceVO);
        }
        return deviceVOS;
    }


    //Unused
    @Override
    public List<DeviceVO> getDeviceByName(String deviceName) {
        List<DeviceVO> deviceVOS = new ArrayList<>();
        List<Device> devices = deviceRepository.findByDeviceName(deviceName);
        for (Device device : devices) {
            DeviceVO deviceVO = new DeviceVO();
            BeanUtils.copyProperties(device,deviceVO);
            deviceVOS.add(deviceVO);
        }
        return deviceVOS;
    }

    @Override
    public List<DeviceVO> getDeviceByStatus(int deviceStatus) {
        List<DeviceVO> deviceVOS = new ArrayList<>();
        List<Device> devices = deviceRepository.findByDeviceStatus(deviceStatus);
        for (Device device : devices) {
            DeviceVO deviceVO = new DeviceVO();
            BeanUtils.copyProperties(device,deviceVO);
            deviceVOS.add(deviceVO);
        }
        return deviceVOS;
    }

    @Override
    public List<DeviceVO> getDeviceByPara(String para) {
        return null;
    }

    @Override
    public List<DeviceVO> getDeviceByTeacherId(Integer teacherId) {
        return null;
    }

    @Override
    public List<DeviceVO> getDeviceByType(String deviceType) {
        return null;
    }

    @Override
    public List<DeviceVO> getDeviceByBuyTime(Date buyTime) {
        return null;
    }

    @Override
    public List<DeviceVO> getDeviceByPrice(String Price) {
        return null;
    }

    @Override
    public List<DeviceVO> getDeviceByManufacture(String manufacture) {
        return null;
    }

    @Override
    public List<DeviceVO> getDeviceByWarranty(Date warranty) {
        return null;
    }

    @Override
    public List<DeviceVO> getDeviceByUser(Integer userId) {
        return null;
    }


}
