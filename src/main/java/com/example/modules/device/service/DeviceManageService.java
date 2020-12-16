package com.example.modules.device.service;

import com.example.modules.device.vo.DeviceAddVO;
import com.example.modules.device.vo.DeviceUpdateVO;
import com.example.modules.device.vo.DeviceVO;

//老师采用权限进行如下操作 因此@Sercure("ROLE_ADMIN")
public interface DeviceManageService {
    /**
     * 老师新购买的设备
     * @param deviceAddVO 老师加入的设备
     */
    void addDevice(DeviceAddVO deviceAddVO);

    /**
     * 老师删除某个设备
     * @param deviceId
     */
    void removeDevice(Integer deviceId);

    /**
     * 老师更新某个设备
     * deviceUpdateVO中id不可为空@NotBlank
     * @param deviceUpdateVO
     */
    void updateDevice(DeviceUpdateVO deviceUpdateVO);

}
