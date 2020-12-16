package com.example.common.authentation;


import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    public MyUsernamePasswordAuthenticationFilter() {
        super(new AntPathRequestMatcher("/api/login","POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        logger.info("Attempt authenticate...");
        if(httpServletRequest.getMethod().equals("POST")){
            String username = this.obtainUsername(httpServletRequest);
            String password = this.obtainPassword(httpServletRequest);
            if(username == null) {
                username = "";
            }
            if(password == null) {
                password = "";
            }

            username = username.trim(); //去除空格
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username,password);
            authRequest.setDetails(this.authenticationDetailsSource.buildDetails(httpServletRequest));
            return this.getAuthenticationManager().authenticate(authRequest);

        } else {
            throw new AuthenticationServiceException("Authentication method not supported: " + httpServletRequest.getMethod());
        }
    }

    protected String obtainPassword(HttpServletRequest httpServletRequest) {
        String passwordPara = "password";
        return httpServletRequest.getParameter(passwordPara);
    }

    protected String obtainUsername(HttpServletRequest httpServletRequest) {
        String usernamePara = "username";
        return httpServletRequest.getParameter(usernamePara);
    }
}
