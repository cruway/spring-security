package com.example.springsecurity.controller.admin;

import com.example.springsecurity.domain.Role;
import com.example.springsecurity.dto.RoleDto;
import com.example.springsecurity.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping(value="/admin/roles")
    public String getRoles(Model model) throws Exception {

        List<Role> roles = roleService.getRoles();
        model.addAttribute("roles", roles);

        return "admin/role/list";
    }

    @GetMapping(value="/admin/roles/register")
    public String viewRoles(Model model) throws Exception {

        RoleDto role = new RoleDto();
        model.addAttribute("role", role);

        return "admin/role/detail";
    }

    @PostMapping(value="/admin/roles")
    public String createRole(RoleDto roleDto) throws Exception {
        Role role = Role.builder()
                .id(Long.valueOf(roleDto.getId()))
                .roleName(roleDto.getRoleName())
                .roleDesc(roleDto.getRoleDesc())
                .build();
        roleService.createRole(role);

        return "redirect:/admin/roles";
    }

    @GetMapping(value="/admin/roles/{id}")
    public String getRole(@PathVariable String id, Model model) throws Exception {
        Role role = roleService.getRole(Long.valueOf(id));
        RoleDto roleDto = RoleDto.builder()
                .id(String.valueOf(role.getId()))
                .roleName(role.getRoleName())
                .roleDesc(role.getRoleDesc())
                .build();
        model.addAttribute("role", roleDto);

        return "admin/role/detail";
    }

    @GetMapping(value="/admin/roles/delete/{id}")
    public String removeResources(@PathVariable String id, Model model) throws Exception {

        Role role = roleService.getRole(Long.valueOf(id));
        roleService.deleteRole(Long.valueOf(id));

        return "redirect:/admin/resources";
    }
}
