package com.example.Tema_3.service;

import com.example.Tema_3.repository.ResourceRightsRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ResourceRightsRoleService {
    @Autowired
    ResourceRightsRoleRepository resourceRightsRoleRepository;

    public void deleteResourceRightsRoleByIdRole(Long id)
    {
        resourceRightsRoleRepository.deleteResourcesRightsRoleByRoleId(id);

    }
}
