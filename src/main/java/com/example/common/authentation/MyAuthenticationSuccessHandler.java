package com.example.common.authentation;

import com.example.common.response.RespBean;
import com.example.common.response.RespCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@Slf4j
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    //@Autowired
    //private UserCache userCache;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info("Login success, loading user info from database");
        //userCache.getUser(authentication.getName());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter out = httpServletResponse.getWriter();

        out.write(new ObjectMapper().writeValueAsString(RespBean.build(RespCode.OK_LOGIN,null)));
        out.flush();
        out.close();
    }
}
