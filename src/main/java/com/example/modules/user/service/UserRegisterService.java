package com.example.modules.user.service;

import com.example.modules.user.vo.UserRegisterVO;

public interface UserRegisterService {

    /**
     * do register in differentways
     * @param userRegisterVO
     */
    void register(UserRegisterVO userRegisterVO);
}
