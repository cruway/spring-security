package com.example.springsecurity.domain;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "ACCESS_IP")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(value = { AuditingEntityListener.class})
@EqualsAndHashCode(of = "id")
public class AccessIp extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "IP_ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "IP_ADDRESS", nullable = false)
    private String ipAddress;

    @Builder
    public AccessIp(Long id, String ipAddress) {
        this.id = id;
        this.ipAddress = ipAddress;
    }
}