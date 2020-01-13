package com.example.Tema_3.repository;

import com.example.Tema_3.models.ResourcesRights;
import com.example.Tema_3.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResourcesRightsRepository extends CrudRepository<ResourcesRights, Long>, JpaRepository<ResourcesRights, Long> {

    ResourcesRights findAllById(Long id);
    List<ResourcesRights> findAll();

}
