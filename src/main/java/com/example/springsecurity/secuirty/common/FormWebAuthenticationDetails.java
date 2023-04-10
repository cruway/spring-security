package com.example.springsecurity.secuirty.common;

import lombok.Getter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * 認証追加機能
 * ・ログイン情報とパスワード情報を活用するための処理
 */
public class FormWebAuthenticationDetails extends WebAuthenticationDetails {

    @Getter
    private String secretKey;

    public FormWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        secretKey = request.getParameter("secret_key");
    }
}
