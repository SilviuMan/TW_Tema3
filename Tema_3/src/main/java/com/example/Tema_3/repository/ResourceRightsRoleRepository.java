package com.example.Tema_3.repository;

import com.example.Tema_3.models.ResourcesRightsRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResourceRightsRoleRepository extends CrudRepository<ResourcesRightsRole, Long>, JpaRepository<ResourcesRightsRole, Long> {
    List<ResourcesRightsRole> findAll();
    void deleteResourcesRightsRoleByRoleId(Long id);
}
