package com.example.springsecurity.service;

import com.example.springsecurity.domain.Resources;

import java.util.List;

public interface ResourcesService {
    Resources getResources(long id);

    List<Resources> getResources();

    void createResources(Resources Resources);

    void deleteResources(long id);
}
