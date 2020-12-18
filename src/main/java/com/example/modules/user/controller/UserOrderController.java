package com.example.modules.user.controller;

import com.example.common.response.RespBean;
import com.example.modules.user.service.TeacherService;
import com.example.modules.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserOrderController {

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Resource(name = "teacherServiceImpl")
    private TeacherService teacherService;


    //UserAPI
    @PutMapping("/api/device/{id}/apply")
    public RespBean applyOneDevice(@PathVariable Integer id) {
        userService.applyOneDevice(id);
        return RespBean.ok();
    }

    @PutMapping("/api/device/{id}/return")
    public RespBean returnOneDevice(@PathVariable Integer id) {
        userService.returnOneDevice(id);
        return RespBean.ok();
    }

    @GetMapping("/api/me/device/order/all")
    public RespBean getAllMyDeviceOrders() {
        return RespBean.ok(userService.getAllMyDeviceOrders());
    }


    @GetMapping("/api/me/device/order/success")
    public RespBean getAllMySuccessDeviceOrders() {
        return RespBean.ok(userService.getAllMySuccessDeviceOrders());
    }

    @GetMapping("/api/me/device/order/failed")
    public RespBean getAllMyFailedDeviceOrders() {
        return RespBean.ok(userService.getAllMyFailedDeviceOrders());
    }

    @GetMapping("/api/me/device/order/enqueue")
    public RespBean getAllMyEnqueueDeviceOrders() {
        return RespBean.ok(userService.getAllMyEnqueueDeviceOrders());
    }

    //TeacherAPI
    @PutMapping("/api/me/device/{deviceId}/order/confirm/{userId}")
    public RespBean confirmOrder(@PathVariable Integer deviceId, @PathVariable Integer userId) {
        teacherService.confirmOrder(deviceId,userId);
        return RespBean.ok();
    }

    @PutMapping("/api/me/device/{deviceId}/repair")
    public RespBean repairDevice(@PathVariable Integer deviceId) {
        teacherService.repairDevice(deviceId);
        return RespBean.ok();
    }

}
