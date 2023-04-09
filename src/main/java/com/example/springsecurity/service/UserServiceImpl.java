package com.example.springsecurity.service;

import com.example.springsecurity.domain.Account;
import com.example.springsecurity.dto.AccountDto;
import com.example.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(AccountDto account) {
        userRepository.save(Account.builder()
                        .userName(account.getUserName())
                        .password(passwordEncoder.encode(account.getPassword()))
                        .email(account.getEmail())
                        .age(account.getAge())
                        .role(account.getRole())
                .build());
    }
}
