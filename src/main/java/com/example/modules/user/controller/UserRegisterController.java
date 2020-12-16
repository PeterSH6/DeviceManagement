package com.example.modules.user.controller;

import com.example.common.response.RespBean;
import com.example.common.response.RespCode;
import com.example.modules.user.service.UserRegisterService;
import com.example.modules.user.vo.UserRegisterVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserRegisterController {

    @Resource(name = "vistorRegisterServiceImpl")
    private UserRegisterService userRegisterService;

    @PostMapping("/api/register")
    public RespBean registerVistor(@RequestBody UserRegisterVO userRegisterVO) {
        userRegisterService.register(userRegisterVO);
        return RespBean.build(RespCode.OK_REGISTER,null);
    }
}
