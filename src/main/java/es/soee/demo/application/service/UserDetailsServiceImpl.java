package es.soee.demo.application.service;


import es.soee.demo.domain.entity.User;
import es.soee.demo.domain.entity.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserApplicationService userApplicationService;

    @Autowired
    public UserDetailsServiceImpl(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userApplicationService.findByEmail(s);
        return UserPrinciple.build(user);
    }
}
