package com.example.springsecurity.repository;

import com.example.springsecurity.domain.Resources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ResourcesRepository extends JpaRepository<Resources, Long>, ResourcesRepositoryQuery, QuerydslPredicateExecutor<Resources> {
    Resources findByResourceNameAndHttpMethod(String resourceName, String httpMethod);
}
