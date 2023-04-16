package com.example.springsecurity.service;

import com.example.springsecurity.domain.Role;

import java.util.List;

public interface RoleService {
    Role getRole(long id);
    List<Role> getRoles();
    void createRole(Role role);
    void deleteRole(long id);
}
