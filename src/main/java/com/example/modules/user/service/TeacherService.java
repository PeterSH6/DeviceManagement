package com.example.modules.user.service;

import com.example.modules.device.vo.DeviceVO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TeacherService {

    void confirmOrder(Integer deviceId, Integer userId);

    void repairDevice(Integer deviceId);

}
