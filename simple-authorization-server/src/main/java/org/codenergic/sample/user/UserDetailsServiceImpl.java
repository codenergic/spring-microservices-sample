package org.codenergic.sample.user;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String arg0)
            throws UsernameNotFoundException {
        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setId("1");
        userDetails.setUsername("dias");
        userDetails.setPassword("{noop}dias");
        userDetails.setAuthorities(
                Collections.singletonList(new SimpleGrantedAuthority("USER")));
        return userDetails;
    }

}
