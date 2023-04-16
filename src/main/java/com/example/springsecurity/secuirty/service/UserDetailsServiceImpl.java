package com.example.springsecurity.secuirty.service;

import com.example.springsecurity.domain.Account;
import com.example.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DBに権限連携処理
 */
@Slf4j
@Service("userDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    private final HttpServletRequest request;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = userRepository.findByUsername(username);
        // user名がない場合、null exception処理
        if (account == null) {
            if (userRepository.countByUsername(username) == 0) {
                throw new UsernameNotFoundException("No user found with username: " + username);
            }
        }
        List<GrantedAuthority> collect = account.getUserRoles()
                .stream()
                .map(userRole -> userRole.getRoleName())
                .collect(Collectors.toSet())
                .stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        //List<GrantedAuthority> collect = userRoles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return new AccountContext(account, collect);
    }
}
