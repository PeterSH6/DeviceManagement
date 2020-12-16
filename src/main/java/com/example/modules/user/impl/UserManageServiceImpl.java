package com.example.modules.user.impl;

import com.example.cache.UserCache;
import com.example.dao.UserRepository;
import com.example.entity.User;
import com.example.modules.user.service.UserManageService;
import com.example.modules.user.vo.UserInfoVO;
import com.example.modules.user.vo.VOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserManageServiceImpl implements UserManageService {

    @Autowired
    private UserCache userCache;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private VOUtils voUtils;

    @Override
    public UserInfoVO getUserInfo() {
        User user = userCache.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        return voUtils.userToUserInfoVO(user);
    }
}
