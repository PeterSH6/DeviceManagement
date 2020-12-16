package com.example.configuration;

import com.example.common.authentation.MyAuthenticationFailureHandler;
import com.example.common.authentation.MyLogoutHandler;
import com.example.common.authentation.MyUserDetailsService;
import com.example.common.authentation.MyUsernamePasswordAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoderInstance() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsServiceInstance() {
        return new MyUserDetailsService();
    }

    @Resource(name = "myAuthenticationSuccessHandler")
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandlerInstance() {
        return new MyAuthenticationFailureHandler();
    }

    @Resource(name = "myLogoutHandler")
    private MyLogoutHandler myLogoutHandler;

    @Bean
    public MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilterInstance() throws Exception {
        MyUsernamePasswordAuthenticationFilter filter = new MyUsernamePasswordAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(authenticationFailureHandlerInstance());
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceInstance()).passwordEncoder(passwordEncoderInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/register")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/api/login")
                .permitAll()
                .loginPage("/api/guard")  // deny unauthorized access
                .permitAll()
                .and()
                .logout()
                .addLogoutHandler(myLogoutHandler)
                .logoutUrl("/api/logout")
                .permitAll()
                .and().cors()
                .and().csrf().disable();
        http.addFilterAt(myUsernamePasswordAuthenticationFilterInstance(), UsernamePasswordAuthenticationFilter.class);
    }

}
