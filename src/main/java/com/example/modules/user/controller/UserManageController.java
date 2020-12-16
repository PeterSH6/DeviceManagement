package com.example.modules.user.controller;

import com.example.common.response.RespBean;
import com.example.common.response.RespCode;
import com.example.modules.user.service.UserManageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserManageController {

    @Resource(name = "userManageServiceImpl")
    private UserManageService userManageService;

    @GetMapping("/api/me")
    public RespBean getUserInfo() {
        return RespBean.ok(userManageService.getUserInfo());
    }

    @GetMapping("/api/guard")
    public RespBean getLogout() {
        return RespBean.build(RespCode.OK_LOGOUT, null);
    }



}
