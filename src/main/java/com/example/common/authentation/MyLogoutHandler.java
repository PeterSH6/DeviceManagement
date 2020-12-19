package com.example.common.authentation;

import com.example.cache.UserCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class MyLogoutHandler implements LogoutHandler {

    @Autowired
    private UserCache userCache;

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        log.info("Logout success," + authentication.getName() + "logout...");
        userCache.putUser(userCache.getUser(authentication.getName()));
        userCache.evictUser(userCache.getUser(authentication.getName()));
    }
}
