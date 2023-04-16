package com.example.springsecurity.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@ToString(exclude = {"userRoles"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = "id", callSuper=false)
public class Account extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private int age;

    @Column
    @Setter
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinTable(name = "account_roles", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id") })
    @Setter
    private Set<Role> userRoles;

    @Builder
    public Account(Long id, String username, String email, int age, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.age = age;
        this.password = password;
        this.userRoles = new HashSet<>();
    }
}
