package com.example.modules.user.controller;

import com.example.common.response.RespBean;
import com.example.modules.user.service.SearchOrderService;
import com.example.modules.user.service.TeacherService;
import com.example.modules.user.service.UserService;
import com.example.modules.user.vo.ReportBrokenVO;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UserOrderController {

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Resource(name = "teacherServiceImpl")
    private TeacherService teacherService;

    @Resource(name = "searchOrderServiceImpl")
    private SearchOrderService searchOrderService;


    //UserAPI
    @PutMapping("/api/device/{id}/apply")
    public RespBean applyOneDevice(@PathVariable Integer id) {
        userService.applyOneDevice(id);
        return RespBean.ok();
    }

    @Secured(value = {"ROLE_TEACHER"})
    @PutMapping("/api/device/{id}/return")
    public RespBean returnOneDevice(@PathVariable Integer id) {
        userService.returnOneDevice(id);
        return RespBean.ok();
    }

    @GetMapping("/api/me/device/order/all")
    public RespBean getAllMyDeviceOrders() {
        return RespBean.ok(searchOrderService.getAllMyDeviceOrders());
    }


    @GetMapping("/api/me/device/order/success")
    public RespBean getAllMySuccessDeviceOrders() {
        return RespBean.ok(searchOrderService.getAllMySuccessDeviceOrders());
    }

    @GetMapping("/api/me/device/order/failed")
    public RespBean getAllMyFailedDeviceOrders() {
        return RespBean.ok(searchOrderService.getAllMyFailedDeviceOrders());
    }

    @GetMapping("/api/me/device/order/enqueue")
    public RespBean getAllMyEnqueueDeviceOrders() {
        return RespBean.ok(searchOrderService.getAllMyEnqueueDeviceOrders());
    }

    @GetMapping("/api/me/device/order/doing")
    public RespBean getAllMyDoingDeviceOrders() {
        return RespBean.ok(searchOrderService.getAllMyDoingDeviceOrders());
    }

    //Teacher只希望看到未分配的设备的Order，occupied设备的order无需展示给teacher
    @GetMapping("/api/me/device/order/enqueue/waiting")
    public RespBean getAllMyEnqueueNotOccupiedDeviceOrders() {
        return RespBean.ok(searchOrderService.getAllMyEnqueueNotOccupiedDeviceOrders());
    }


    //TeacherAPI
    @PutMapping("/api/me/device/{deviceId}/order/confirm/{userId}")
    public RespBean confirmOrder(@PathVariable Integer deviceId, @PathVariable Integer userId) {
        teacherService.confirmOrder(deviceId,userId);
        return RespBean.ok();
    }

    @PostMapping("/api/me/device/report/repair")
    public RespBean repairDevice(@RequestBody ReportBrokenVO reportBrokenVO) {
        teacherService.repairDevice(reportBrokenVO);
        return RespBean.ok();
    }

}
