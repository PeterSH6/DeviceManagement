package com.example.modules.user.service;

import com.example.modules.device.vo.DeviceVO;
import com.example.modules.user.vo.ReportBrokenVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TeacherService {

    void confirmOrder(Integer deviceId, Integer userId);

    @Transactional
    void repairDevice(ReportBrokenVO reportBrokenVO);

}
