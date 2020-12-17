package com.example.modules.device.controller;

import com.example.common.response.RespBean;
import com.example.common.response.RespCode;
import com.example.entity.Device;
import com.example.entity.User;
import com.example.modules.device.service.DeviceManageService;
import com.example.modules.device.service.DeviceSearchService;
import com.example.modules.device.vo.DeviceAddVO;
import com.example.modules.device.vo.DeviceUpdateVO;
import com.example.modules.device.vo.DeviceVO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
public class DeviceManageController {

    @Resource(name = "deviceSearchServiceImpl")
    private DeviceSearchService deviceSearchService;

    @Resource(name = "deviceManageService")
    private DeviceManageService deviceManageService;

    //Device add remove update
    @PostMapping("/api/device")
    public RespBean addDevice(@Validated @RequestBody DeviceAddVO deviceAddVO) {
        deviceManageService.addDevice(deviceAddVO);
        return RespBean.ok("add device ok");
    }

    @DeleteMapping("/api/device")
    public RespBean removeDevice(@RequestParam(name = "deviceId") Integer deviceId) {
        deviceManageService.removeDevice(deviceId);
        return RespBean.ok("remove device ok");
    }

    @PutMapping("/api/device")
    public RespBean putDevice(@Validated @RequestBody DeviceUpdateVO deviceUpdateVO) {
        deviceManageService.updateDevice(deviceUpdateVO);
        return RespBean.ok("remove device ok");
    }

    //Device Search 使用装饰者模式
    @GetMapping("/api/device/all")
    public RespBean getAllDevices() {
        return RespBean.build(RespCode.AD_GET,deviceSearchService.getAllDevice());
    }

    //多种类型匹配 非String的匹配均为exact()
    @GetMapping("/api/device")
    public RespBean getDeviceByConditions(
            @RequestParam(name = "deviceId",required = false) Integer deviceId,
            @RequestParam(name = "deviceStatus",required = false) Integer deviceStatus,
            @RequestParam(name = "deviceName",required = false) String deviceName,
            @RequestParam(name = "deviceType",required = false) String deviceType,
            @RequestParam(name = "devicePara",required = false) String devicePara,
            @RequestParam(name = "deviceManufacture",required = false) String deviceManufacture,
            @RequestParam(name = "devicePrice",required = false) String devicePrice,
            @RequestParam(name = "deviceTeacherId",required = false) Integer deviceTeacherId,
            @RequestParam(name = "deviceBuyTime",required = false) Date deviceBuyTime,
            @RequestParam(name = "deviceWarranty",required = false) Date deviceWarranty
    ) {
        Device device = new Device();
        ExampleMatcher matcher = ExampleMatcher.matching();
        if(deviceId != null) {
            device.setDeviceId(deviceId);
            matcher = matcher.withMatcher("deviceId", ExampleMatcher.GenericPropertyMatchers.exact());
        } else {
            matcher = matcher.withIgnorePaths("deviceId");
        }

        if(deviceStatus != null) {
            device.setDeviceStatus(deviceStatus);
            matcher = matcher.withMatcher("deviceStatus", ExampleMatcher.GenericPropertyMatchers.exact());
        } else {
            matcher = matcher.withIgnorePaths("deviceStatus");
        }
        //Name: 模糊匹配
        if(deviceName != null) {
            device.setDeviceName(deviceName);
            matcher = matcher.withMatcher("deviceName", ExampleMatcher.GenericPropertyMatchers.contains());
        } else {
            matcher = matcher.withIgnorePaths("deviceName");
        }

        if(deviceType != null) {
            device.setDeviceType(deviceType);
            matcher = matcher.withMatcher("deviceType", ExampleMatcher.GenericPropertyMatchers.contains());
        } else {
            matcher = matcher.withIgnorePaths("deviceType");
        }

        if(deviceManufacture != null) {
            device.setDeviceManufacture(deviceManufacture);
            matcher = matcher.withMatcher("deviceManufacture", ExampleMatcher.GenericPropertyMatchers.contains());
        } else {
            matcher = matcher.withIgnorePaths("deviceManufacture");
        }

        if(devicePara != null) {
            device.setDevicePara(devicePara);
            matcher = matcher.withMatcher("devicePara", ExampleMatcher.GenericPropertyMatchers.contains());
        } else {
            matcher = matcher.withIgnorePaths("devicePara");
        }

        if(devicePrice != null) {
            device.setDevicePrice(devicePrice);
            matcher = matcher.withMatcher("devicePrice", ExampleMatcher.GenericPropertyMatchers.exact());
        } else {
            matcher = matcher.withIgnorePaths("devicePrice");
        }

        //TODO: Maybe some problem because used @OneToOne
        // user or teacherId
        if(deviceTeacherId != null) {
            User user = new User();
            user.setUserId(deviceTeacherId);
            device.setUser(user);
            matcher = matcher.withMatcher("user", ExampleMatcher.GenericPropertyMatchers.contains());
        } else {
            matcher = matcher.withIgnorePaths("user");
        }

        if(deviceBuyTime != null) {
            device.setBuyTime(deviceBuyTime);
            matcher = matcher.withMatcher("buyTime", ExampleMatcher.GenericPropertyMatchers.exact());
        } else {
            matcher = matcher.withIgnorePaths("buyTime");
        }

        if(deviceWarranty != null) {
            device.setWarranty(deviceWarranty);
            matcher = matcher.withMatcher("warranty", ExampleMatcher.GenericPropertyMatchers.exact());
        } else {
            matcher = matcher.withIgnorePaths("warranty");
        }
        matcher = matcher.withIgnorePaths("broken");

        Example<Device> example = Example.of(device,matcher);
        List<DeviceVO> result = deviceSearchService.getAllDevice(example);
        return RespBean.ok(result);
    }

}
