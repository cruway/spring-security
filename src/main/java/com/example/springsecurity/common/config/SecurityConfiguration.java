package com.example.springsecurity.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // login権限
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/admin/pay").access("hasRole('ADMIN') or hasRole('SYS')")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated();

        http
                .formLogin()
                .successHandler((request, response, authentication) -> {
                    RequestCache requestCache = new HttpSessionRequestCache();
                    SavedRequest savedRequest = requestCache.getRequest(request, response);
                    String redirectUrl = savedRequest.getRedirectUrl();
                    response.sendRedirect(redirectUrl);
                });

        http
                .exceptionHandling()
                /*.authenticationEntryPoint((request, response, authException) -> {
                    response.sendRedirect("/login");
                })*/
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.sendRedirect("/denied");
                });
        //.loginPage("/loginPage") // ユーザ定義ログインページ
                /*.defaultSuccessUrl("/") // ログイン成功移動ページ
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
                .permitAll();*/

        /*http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .addLogoutHandler((request, response, authentication) -> {
                    HttpSession session = request.getSession();
                    session.invalidate();
                })
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.sendRedirect("/login");
                })
                .deleteCookies("remember-me")
                .and()
                .rememberMe()
                .rememberMeParameter("remember")
                .tokenValiditySeconds(3600)
                .userDetailsService(userDetailsService);*/
        /*http
                .rememberMe()
                .userDetailsService(userDetailsService);*/

        /*http.sessionManagement(session -> session
                        .sessionFixation().changeSessionId() // セッション固定保護 none, migrateSession, newSession
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // セッション政策
                        .invalidSessionUrl("/invalid")
                        .maximumSessions(1) // セッショn最大許容数, -1: 無制限ログインセッション許容
                        .maxSessionsPreventsLogin(true) // 同時ログイン防止, false: 既存セッション切れ(default)
                        .expiredUrl("/expired")); // セッションが切れた場合のページ*/
        return http.build();
    }

    @Bean
    public UserDetailsManager userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password("{noop}1111")
                .roles("USER")
                .build();

        UserDetails sys = User.builder()
                .username("sys")
                .password("{noop}1111")
                .roles("SYS")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}1111")
                .roles("ADMIN", "SYS", "USER")
                .build();

        return new InMemoryUserDetailsManager(user, sys, admin);
    }
}
