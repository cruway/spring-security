package com.example.springsecurity.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "PERSISTENT_LOGINS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PersistentLogin extends BaseEntity {
    private static final long serialVersionUID = 8433999509932007961L;
    @Id
    private String series;
    private String username;
    private String token;
    private Date lastUsed;

    @Builder
    public PersistentLogin(PersistentRememberMeToken token){
        this.series = token.getSeries();
        this.username = token.getUsername();
        this.token = token.getTokenValue();
        this.lastUsed = token.getDate();
    }
}