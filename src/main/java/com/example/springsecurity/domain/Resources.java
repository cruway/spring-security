package com.example.springsecurity.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "RESOURCES")
@Getter
@ToString(exclude = {"roleSet"})
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Resources implements Serializable {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column(name = "resource_name")
    private String resourceName;

    @Column(name = "http_method")
    private String httpMethod;

    @Column(name = "order_num")
    private int orderNum;

    @Column(name = "resource_type")
    private String resourceType;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_resources", joinColumns = {
            @JoinColumn(name = "resource_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roleSet;

    public Resources(Long id, String resourceName, String httpMethod, int orderNum, String resourceType) {
        this.id = id;
        this.resourceName = resourceName;
        this.httpMethod = httpMethod;
        this.orderNum = orderNum;
        this.resourceType = resourceType;
        this.roleSet = new HashSet<>();
    }
}
