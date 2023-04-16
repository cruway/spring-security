package com.example.springsecurity.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "ROLE")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = "id", callSuper=false)
public class Role extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_desc")
    private String roleDesc;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roleSet")
    @OrderBy("orderNum desc")
    private Set<Resources> resourcesSet;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "userRoles")
    private Set<Account> accounts;

    @Builder
    public Role(Long id, String roleName, String roleDesc) {
        this.id = id;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
        this.resourcesSet = new HashSet<>();
        this.accounts = new HashSet<>();
    }
}
