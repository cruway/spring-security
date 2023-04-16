package com.example.springsecurity.controller.user;

import com.example.springsecurity.domain.Account;
import com.example.springsecurity.dto.AccountDto;
import com.example.springsecurity.repository.RoleRepository;
import com.example.springsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final RoleRepository roleRepository;

    @GetMapping("/users")
    public String createUser() {
        return "user/login/register";
    }

    @PostMapping(value = "/users")
    public String createUser(AccountDto accountDto) throws Exception {
        userService.createUser(accountDto);
        return "redirect:/";
    }

    @GetMapping(value = "/mypage")
    public String myPage(@AuthenticationPrincipal Account account, Authentication authentication, Principal principal) throws Exception {
        return "user/mypage";
    }
}
