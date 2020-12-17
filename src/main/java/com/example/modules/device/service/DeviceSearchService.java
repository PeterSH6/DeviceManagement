package com.example.modules.device.service;


import com.example.common.constant.DeviceStatus;
import com.example.dao.DeviceRepository;
import com.example.entity.Device;
import com.example.modules.device.vo.DeviceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.util.Date;
import java.util.List;

public interface DeviceSearchService {

    List<DeviceVO> getAllDevice();

    List<DeviceVO> getAllDevice(Example example);

    List<DeviceVO> getDeviceByName(String deviceName);

    List<DeviceVO> getDeviceByStatus(int deviceStatus);

    List<DeviceVO> getDeviceByPara(String para);

    List<DeviceVO> getDeviceByTeacherId(Integer teacherId);

    List<DeviceVO> getDeviceByType(String deviceType);

    List<DeviceVO> getDeviceByBuyTime(Date buyTime);

    List<DeviceVO> getDeviceByPrice(String Price);

    List<DeviceVO> getDeviceByManufacture(String manufacture);

    List<DeviceVO> getDeviceByWarranty(Date warranty);

    List<DeviceVO> getDeviceByUser(Integer userId);

}
