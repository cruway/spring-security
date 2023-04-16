package com.example.springsecurity.repository;

import com.example.springsecurity.domain.Resources;

import java.util.List;

public class ResourcesRepositoryImpl implements ResourcesRepositoryQuery {
    @Override
    public List<Resources> findAllResources() {
        return null;
    }

    @Override
    public List<Resources> findAllMethodResources() {
        return null;
    }

    @Override
    public List<Resources> findAllPointcutResources() {
        return null;
    }
}
