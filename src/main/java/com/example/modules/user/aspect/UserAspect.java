package com.example.modules.user.aspect;

import com.example.modules.user.vo.UserRegisterVO;
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

    //SearchService
    @Pointcut("execution(void com.example.modules.user.service.SearchOrderService.*(..))")
    public void searchOrderServicePC() {}

    //UserRegisterService
    @Pointcut("execution(void com.example.modules.user.service.UserRegisterService.register(com.example.modules.user.vo.UserRegisterVO)) && args(userRegisterVO)")
    public void userRegisterPC(UserRegisterVO userRegisterVO) {}

}
