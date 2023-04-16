package com.example.springsecurity.service.impl;

import com.example.springsecurity.domain.Resources;
import com.example.springsecurity.repository.ResourcesRepository;
import com.example.springsecurity.service.ResourcesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResourcesServiceImpl implements ResourcesService {

    private final ResourcesRepository resourcesRepository;

    @Override
    public Resources getResources(long id) {
        return resourcesRepository.findById(id).orElse(Resources.builder().build());
    }

    @Override
    public List<Resources> getResources() {
        return resourcesRepository.findAll(Sort.by(Sort.Order.asc("orderNum")));
    }

    @Override
    public void createResources(Resources resources) {
        resourcesRepository.save(resources);
    }

    @Override
    public void deleteResources(long id) {
        resourcesRepository.deleteById(id);
    }
}
