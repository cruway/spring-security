package com.example.springsecurity.service;

import com.example.springsecurity.domain.Account;
import com.example.springsecurity.dto.AccountDto;

import java.util.List;

public interface UserService {
    void createUser(AccountDto accountDto);

    void modifyUser(AccountDto accountDto);

    List<Account> getUsers();

    AccountDto getUser(Long id);

    void deleteUser(Long id);
}
