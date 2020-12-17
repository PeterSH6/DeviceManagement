package com.example.modules.device.aspect;

import com.example.modules.device.vo.DeviceAddVO;
import com.example.modules.device.vo.DeviceUpdateVO;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 设备的增删改需要验证用户的权限 使用AOP
 */
@Aspect
public class DeviceAspect {
    //DeviceManageService
    @Pointcut("execution(void com.example.modules.device.service.DeviceManageService.removeDevice(Integer)) && args(deviceId)")
    public void deviceManageServiceRemoveDevicePC(Integer deviceId) {}

    @Pointcut("execution(void com.example.modules.device.service.DeviceManageService.addDevice(com.example.modules.device.vo.DeviceAddVO)) && args(deviceAddVO)")
    public void deviceManageServiceAddDevicePC(DeviceAddVO deviceAddVO) {}

    @Pointcut("execution(void com.example.modules.device.service.DeviceManageService.updateDevice(com.example.modules.device.vo.DeviceUpdateVO)) && args(deviceUpdateVO)")
    public void deviceManageServiceUpdateDevicePC(DeviceUpdateVO deviceUpdateVO) {}
}
