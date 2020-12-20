package com.example.modules.user.impl;

import com.example.dao.UserRepository;
import com.example.entity.User;
import com.example.modules.user.service.UserRegisterService;
import com.example.modules.user.vo.UserRegisterVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class VistorRegisterServiceImpl implements UserRegisterService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void register(UserRegisterVO userRegisterVO) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        BeanUtils.copyProperties(userRegisterVO,user);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialNonExpired(true);
        user.setEnabled(true);
        //user.setAuthorities("ROLE_STUDENT");
        user.setPassWord(passwordEncoder.encode(userRegisterVO.getPassWord()));
        userRepository.save(user);
    }
}
