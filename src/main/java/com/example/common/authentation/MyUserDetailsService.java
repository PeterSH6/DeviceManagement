package com.example.common.authentation;


import com.example.dao.UserRepository;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(s);
        if (user == null) {
            throw new UsernameNotFoundException("can't find username: " + s);
        } else {
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getPassWord(),
                    user.getEnabled(),
                    user.getAccountNonExpired(),
                    user.getCredentialNonExpired(),
                    user.getAccountNonLocked(),
                    AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities())
            );
        }
    }
}
