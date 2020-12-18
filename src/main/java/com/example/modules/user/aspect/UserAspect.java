package com.example.modules.user.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class UserAspect {

    //UserService
    @Pointcut("execution(void com.example.modules.user.service.UserService.applyOneDevice(Integer)) && args(deviceId)")
    public void  userServiceApplyOneDevicePC(Integer deviceId) {}

    //TeacherService
    //Authenticaiton Validation
    @Pointcut("execution(void com.example.modules.user.service.TeacherService.*(..))")
    public void teacherServiceAuthenticationPC() {}

    //ConfirmOrderValidation
    @Pointcut("execution(void com.example.modules.user.service.TeacherService.confirmOrder(Integer,Integer)) && args(deviceId,userId)")
    public void teacherServiceConfirmOrderPC(Integer deviceId, Integer userId) {}



    //TODO:对于超时归还的违约情况记录 @After afterReturnDevice

}
