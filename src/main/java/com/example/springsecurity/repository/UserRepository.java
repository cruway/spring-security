package com.example.springsecurity.repository;

import com.example.springsecurity.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String username);
    int countByUsername(String username);
}
