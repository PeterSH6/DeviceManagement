package com.example.modules.user.service;

import com.example.modules.user.vo.DeviceOrderVO;
import com.example.modules.user.vo.DeviceVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService {

    void confrimOrder(Integer deviceId, Integer userId);

    void repairDevice(Integer deviceId);

    List<DeviceVO> getAllMyDevice();


}
