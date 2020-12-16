package com.example.modules.user.impl;

import com.example.dao.OrderRepository;
import com.example.dao.UserRepository;
import com.example.modules.user.service.TeacherService;
import com.example.modules.user.vo.DeviceOrderVO;
import com.example.modules.user.vo.DeviceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void confrimOrder(Integer deviceId, Integer userId) {

    }

    @Override
    public void repairDevice(Integer deviceId) {

    }

    @Override
    public List<DeviceOrderVO> getAllEnqueOrder() {
        return null;
    }

    @Override
    public List<DeviceVO> getAllMyDevice() {
        return null;
    }
}
