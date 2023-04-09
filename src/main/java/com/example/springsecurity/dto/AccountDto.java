package com.example.springsecurity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDto {

    private String userName;
    private String password;
    private String email;
    private String age;
    private String role;
}
