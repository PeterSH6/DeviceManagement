package com.example.modules.user.vo;

import com.example.cache.UserCache;
import com.example.dao.OrderRepository;
import com.example.dao.UserRepository;
import com.example.entity.Device;
import com.example.entity.Order;
import com.example.entity.User;
import com.example.modules.device.vo.DeviceVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VOUtils {

    @Autowired
    private UserCache userCache;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    public UserInfoVO userToUserInfoVO(User user) {
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(user,userInfoVO);
        //set totalOrderNum
        userInfoVO.setTotalDeviceNum(orderRepository.findByUser(user).size());
        //set isAdmin
        userInfoVO.setIsAdmin(user.getAuthorities().contains("ROLE_ADMIN"));
        return userInfoVO;
    }

    public List<UserInfoVO> usersToUserInfoVOs(List<User> users) {
        List<UserInfoVO> userInfoVOS = new ArrayList<>();
        for(User user : users) {
            UserInfoVO userInfoVO = new UserInfoVO();
            BeanUtils.copyProperties(user,userInfoVO);
            //set totalOrderNum
            userInfoVO.setTotalDeviceNum(orderRepository.findByUser(user).size());
            //set isAdmin
            userInfoVO.setIsAdmin(user.getAuthorities().contains("ROLE_ADMIN"));
            userInfoVOS.add(userInfoVO);
        }
        return userInfoVOS;
    }

    public DeviceVO deviceTODeviceVO(Device device) {
        DeviceVO deviceVO = new DeviceVO();
        BeanUtils.copyProperties(device,deviceVO);
        return deviceVO;
    }

    public List<DeviceOrderVO> deviceOrderTODeviceOrderVOS(List<Order> orders) {
        List<DeviceOrderVO> deviceOrderVOS = new ArrayList<>();
        for(Order order : orders) {
            DeviceOrderVO deviceOrderVO = new DeviceOrderVO();
            BeanUtils.copyProperties(order,deviceOrderVO);
            deviceOrderVO.setUser(this.userToUserInfoVO(order.getUser()));
            deviceOrderVO.setTeacher(this.userToUserInfoVO(order.getDevice().getUser()));
            deviceOrderVO.setDevice(this.deviceTODeviceVO(order.getDevice()));
        }
        return deviceOrderVOS;
    }

}
