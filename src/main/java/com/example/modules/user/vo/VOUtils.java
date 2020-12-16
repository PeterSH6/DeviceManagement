package com.example.modules.user.vo;

import com.example.cache.UserCache;
import com.example.dao.OrderRepository;
import com.example.dao.UserRepository;
import com.example.entity.Order;
import com.example.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        //set totalOrderNum TODO:多条件查询
        //userInfoVO.setTotalDeviceNum();
        //set isAdmin
        userInfoVO.setIsAdmin(user.getAuthorities().contains("ROLE_ADMIN"));
        return userInfoVO;
    }

}
