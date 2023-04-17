package com.example.springsecurity.service.impl;

import com.example.springsecurity.domain.Account;
import com.example.springsecurity.domain.Role;
import com.example.springsecurity.dto.AccountDto;
import com.example.springsecurity.repository.RoleRepository;
import com.example.springsecurity.repository.UserRepository;
import com.example.springsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(AccountDto accountDto) {
        Account account = Account.builder()
                .username(accountDto.getUsername())
                .password(passwordEncoder.encode(accountDto.getPassword()))
                .email(accountDto.getEmail())
                .age(accountDto.getAge())
                .build();

        Role role = roleRepository.findByRoleName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        account.setUserRoles(roles);
        userRepository.save(account);
    }

    @Override
    public void modifyUser(AccountDto accountDto) {
        Account account = Account.builder()
                .id(Long.valueOf(accountDto.getId()))
                .username(accountDto.getUsername())
                .password(accountDto.getPassword())
                .email(accountDto.getEmail())
                .age(accountDto.getAge())
                .build();

        if (accountDto.getRoles() != null) {
            Set<Role> roles = new HashSet<>();
            accountDto.getRoles().forEach(role -> {
                Role r = roleRepository.findByRoleName(role);
                roles.add(r);
            });
            account.setUserRoles(roles);
        }
        account.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        userRepository.save(account);

    }

    @Override
    public AccountDto getUser(Long id) {
        Account account = userRepository.findById(id).orElse(Account.builder().build());
        AccountDto accountDto = AccountDto.builder()
                .id(String.valueOf(account.getId()) )
                .username(account.getUsername())
                .password(account.getPassword())
                .email(account.getEmail())
                .age(account.getAge())
                .build();
        List<String> roles = account.getUserRoles()
                .stream()
                .map(role -> role.getRoleName())
                .collect(Collectors.toList());

        accountDto.setRoles(roles);
        return accountDto;
    }

    @Override
    public List<Account> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long idx) {
        userRepository.deleteById(idx);
    }
}
