package com.example.modules.repair.aspect;

import com.example.common.exception.BusinessException;
import com.example.common.response.RespCode;
import com.example.dao.BrokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author https://github.com/anlowee
 */
@Aspect
@Component
@Slf4j
public class RepairValidation {

    @Autowired
    private BrokenRepository brokenRepository;

    @Before("com.example.modules.repair.aspect.BrokenAspect.repairServiceRepairPC(Integer) && args(deviceId)")
    public void beforeRepairServiceRepairPC(Integer deviceId) {
        log.info("Validating before repair...");
        if (brokenRepository.findByDevice_DeviceId(deviceId) == null) {
            throw new BusinessException(RespCode.ERR_REPAIR);
        }
    }

}
