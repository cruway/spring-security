package com.example.springsecurity.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // login権限
        http.authorizeHttpRequests()
                .anyRequest()
                .authenticated();

        http.formLogin()
                //.loginPage("/loginPage") // ユーザ定義ログインページ
                .defaultSuccessUrl("/") // ログイン成功移動ページ
                .failureUrl("/login") // ログイン失敗移動ページ
                .usernameParameter("userId") // IDパラメータ名設定
                .passwordParameter("password") // パスワードパラメタ名設定
                .loginProcessingUrl("/login_proc") // ログインForm Action Url
                .successHandler((request, response, authentication) -> {
                    System.out.println("authentication = " + authentication.getName());
                    response.sendRedirect("/");
                }) // ログイン成功後、ハンドラー
                .failureHandler((request, response, exception) -> {
                    System.out.println("exception = " + exception.getMessage());
                    response.sendRedirect("/login");
                }) // ログイン失敗後、ハンドラー
                .permitAll();
        return http.build();
    }
}
