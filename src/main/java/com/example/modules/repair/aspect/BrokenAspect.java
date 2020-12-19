package com.example.modules.repair.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author https://github.com/anlowee
 */
@Aspect
@Component
@Slf4j
public class BrokenAspect {

    @Pointcut(value = "execution(Boolean com.example.modules.repair.service.RepairService.repair(Integer)) && args(deviceId)")
    public void repairServiceRepairPC(Integer deviceId) {}

}
