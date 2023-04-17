package com.example.springsecurity.repository;

import com.example.springsecurity.domain.Resources;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.springsecurity.domain.QResources.resources;
import static com.example.springsecurity.domain.QRole.role;

@RequiredArgsConstructor
public class ResourcesRepositoryImpl implements ResourcesRepositoryQuery {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Resources> findAllResources() {
        return queryFactory
                .selectFrom(resources)
                .join(resources.roleSet, role)
                .where(resources.resourceType.eq("url"))
                .orderBy(resources.orderNum.desc())
                .fetch();
    }

    @Override
    public List<Resources> findAllMethodResources() {
        return queryFactory
                .selectFrom(resources)
                .join(resources.roleSet, role)
                .where(resources.resourceType.eq("method"))
                .orderBy(resources.orderNum.desc())
                .fetch();
    }

    @Override
    public List<Resources> findAllPointcutResources() {
        return queryFactory
                .selectFrom(resources)
                .join(resources.roleSet, role)
                .where(resources.resourceType.eq("pointcut"))
                .orderBy(resources.orderNum.desc())
                .fetch();
    }
}
