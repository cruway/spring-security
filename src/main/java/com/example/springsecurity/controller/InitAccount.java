package com.example.springsecurity.controller;

import com.example.springsecurity.domain.Account;
import com.example.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Profile("initAccount")
@Component
@RequiredArgsConstructor
public class InitAccount {

    private final InitAccountService initAccountService;

    @PostConstruct
    public void init() {
        initAccountService.init();
    }

    @Component
    @RequiredArgsConstructor
    static class InitAccountService {

        private final UserRepository userRepository;

        private final PasswordEncoder passwordEncoder;

        @Transactional
        public void init() {
            int count = userRepository.findAll().size();
            if (count == 0) {
                userRepository.save(Account.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("1111"))
                        .email("test@test.com")
                        .age("25")
                        .role("ROLE_ADMIN")
                        .build());
            }
        }
    }
}
