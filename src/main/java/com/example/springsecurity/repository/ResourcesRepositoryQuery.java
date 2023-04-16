package com.example.springsecurity.repository;

import com.example.springsecurity.domain.Resources;

import java.util.List;

public interface ResourcesRepositoryQuery {
    List<Resources> findAllResources();

    List<Resources> findAllMethodResources();

    List<Resources> findAllPointcutResources();
}
