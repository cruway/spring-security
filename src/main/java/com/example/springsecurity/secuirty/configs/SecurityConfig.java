package com.example.springsecurity.secuirty.configs;

import com.example.springsecurity.secuirty.handler.CustomAccessDeniedHandler;
import com.example.springsecurity.secuirty.provider.FormAuthenticationProvider;
import com.example.springsecurity.secuirty.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationSuccessHandler customAuthenticationSuccessHandler;

    private final AuthenticationFailureHandler customAuthenticationFailureHandler;

    private final AuthenticationDetailsSource authenticationDetailsSource;

    private final CustomUserDetailService customUserDetailService;

    /**
     * CustomUserDetailServiceで設定したuserDetailServiceをここで設定する(なんか以前バージョンとは作りが違うらしい)
     * AccountContext, CustomUserDetailService
     */
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new FormAuthenticationProvider(customUserDetailService, passwordEncoder());
    }

    /**
     * role設定(local設定なので上記のコードと同時に使うとstackoverflowが発生する(aopなんとか))
     * @return
     */
    /*@Bean
    public UserDetailsManager userDetailsService() {
        String password = passwordEncoder().encode("1111");

        UserDetails user = User.builder()
                .username("user")
                .password(password)
                .roles("USER")
                .build();

        UserDetails manager = User.builder()
                .username("manager")
                .password(password)
                .roles("MANAGER", "USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(password)
                .roles("ADMIN", "USER", "MANAGER")
                .build();

        return new InMemoryUserDetailsManager(user, manager, admin);
    }*/

    /**
     * パスワード変換
     *
     * 暗証番号を安全に暗号化する
     *
     * 暗号化フォーマットは:{id}encodedPassword
     * ・基本フォーマットはBcrypt:{bcrypt} $ea$1040148241247127148120481248120480.fqvM/BG
     * ・アルゴリズム種類；bcrypt, noop, pbkdf2, scrypt, sha256
     *
     * インタフェース
     * ・encode(password)；パスワード暗号化
     * matches(rawPassword, encodedPassword)
     * ・パスワード比較
      */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /** static resource管理
     *
     * js/css/imageファイルなど、セキュリティフィルターを適用する必要がないresourceを設定
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/users", "user/login/**", "/login").permitAll() // 権限すべて許可
                .antMatchers("/mypage").hasRole("USER")
                .antMatchers("/messages").hasRole("MANAGER")
                .antMatchers("/config").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login") // ログインページ移動
                .loginProcessingUrl("/login_proc") // ログインフォーム処理
                .defaultSuccessUrl("/") // ログイン後のページ
                .authenticationDetailsSource(authenticationDetailsSource) // 詳細認証
                .successHandler(customAuthenticationSuccessHandler) // 認証成功ハンドラー
                .failureHandler(customAuthenticationFailureHandler) // 認証失敗ハンドラー
                .permitAll();

        http
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler());
        return http.build();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        CustomAccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();
        accessDeniedHandler.setErrorPage("/denied");
        return accessDeniedHandler;
    }
}
