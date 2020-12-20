package com.example.modules.user.aspect;

import com.example.cache.UserCache;
import com.example.common.exception.BusinessException;
import com.example.common.response.RespCode;
import com.example.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@Order(0)
public class TeacherAuthenticationValidation {

    @Autowired
    private UserCache userCache;

    @Before(value = "com.example.modules.user.aspect.UserAspect.teacherServiceAuthenticationPC()")
    public void beforeTeacherServicePC() {
        log.info("Validating the authority of all doing teacherService");
        User user = userCache.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if(!user.getAuthorities().contains("ROLE_TEACHER")) {
            throw new BusinessException(RespCode.AD);
        }
    }
}
