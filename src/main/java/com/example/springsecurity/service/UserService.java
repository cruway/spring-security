package com.example.springsecurity.service;

import com.example.springsecurity.dto.AccountDto;

public interface UserService {
    void createUser(AccountDto account);
}
