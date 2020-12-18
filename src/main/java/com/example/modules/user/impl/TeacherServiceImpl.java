package com.example.modules.user.impl;

import com.example.dao.OrderRepository;
import com.example.dao.UserRepository;
import com.example.modules.user.service.TeacherService;
import com.example.modules.user.vo.DeviceOrderVO;
import com.example.modules.device.vo.DeviceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    //TODO::
    @Override
    public void confrimOrder(Integer deviceId, Integer userId) {

    }

    //TODO::
    @Override
    public void repairDevice(Integer deviceId) {

    }

    //TODO::

    @Override
    public List<DeviceVO> getAllMyDevice() {
        return null;
    }
}
