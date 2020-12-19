package com.example.modules.repair.controller;

import com.example.common.response.RespBean;
import com.example.common.response.RespCode;
import com.example.modules.repair.service.RepairService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author https://github.com/anlowee
 */
@RestController
public class RepairController {

    @Resource(name = "repairServiceImpl")
    private RepairService repairService;

    @DeleteMapping("/api/device/broken")
    public RespBean repair(@RequestParam(name = "deviceId") Integer deviceId) {
        if (repairService.repair(deviceId)) {
            return RespBean.build(RespCode.OK_REPAIR, null);
        } else {
            return RespBean.build(RespCode.OK_CANNOT_REPAIR, null);
        }
    }

}
