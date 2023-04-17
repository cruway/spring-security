package com.example.springsecurity.controller.admin;

import com.example.springsecurity.domain.Resources;
import com.example.springsecurity.domain.Role;
import com.example.springsecurity.dto.ResourcesDto;
import com.example.springsecurity.repository.RoleRepository;
import com.example.springsecurity.service.ResourcesService;
import com.example.springsecurity.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class ResourcesController {

    private final ResourcesService resourcesService;

    private final RoleRepository roleRepository;

    private final RoleService roleService;

    @GetMapping(value="/admin/resources")
    public String getResources(Model model) throws Exception {

        List<Resources> resources = resourcesService.getResources();
        model.addAttribute("resources", resources);

        return "admin/resource/list";
    }

    @PostMapping(value="/admin/resources")
    public String createResources(ResourcesDto resourcesDto) throws Exception {
        Role role = roleRepository.findByRoleName(resourcesDto.getRoleName());
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        Resources resources = Resources.builder()
                .resourceName(resourcesDto.getResourceName())
                .resourceType(resourcesDto.getResourceType())
                .httpMethod(resourcesDto.getHttpMethod())
                .orderNum(resourcesDto.getOrderNum())
                .build();
        resources.setRoleSet(roles);

        resourcesService.createResources(resources);

        return "redirect:/admin/resources";
    }

    @GetMapping(value="/admin/resources/register")
    public String viewRoles(Model model) throws Exception {

        List<Role> roleList = roleService.getRoles();
        model.addAttribute("roleList", roleList);

        ResourcesDto resources = new ResourcesDto();
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(Role.builder().build());
        resources.setRoleSet(roleSet);
        model.addAttribute("resources", resources);

        return "admin/resource/detail";
    }

    @GetMapping(value="/admin/resources/{id}")
    public String getResources(@PathVariable String id, Model model) throws Exception {

        List<Role> roleList = roleService.getRoles();
        model.addAttribute("roleList", roleList);
        Resources resources = resourcesService.getResources(Long.valueOf(id));

        ResourcesDto resourcesDto = ResourcesDto.builder()
                .id(String.valueOf(resources.getId()))
                .resourceName(resources.getResourceName())
                .resourceType(resources.getResourceType())
                .orderNum(resources.getOrderNum())
                .httpMethod(resources.getHttpMethod())
                .roleSet(resources.getRoleSet())
                .build();
        model.addAttribute("resources", resourcesDto);

        return "admin/resource/detail";
    }

    @GetMapping(value="/admin/resources/delete/{id}")
    public String removeResources(@PathVariable String id, Model model) throws Exception {

        Resources resources = resourcesService.getResources(Long.valueOf(id));
        resourcesService.deleteResources(Long.valueOf(id));

        return "redirect:/admin/resources";
    }
}