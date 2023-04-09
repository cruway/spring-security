package com.example.springsecurity.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Account extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    private String password;
    private String email;
    private String age;
    private String role;

    @Builder
    public Account(String userName, String password, String email, String age, String role) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.age = age;
        this.role = role;
    }
}
