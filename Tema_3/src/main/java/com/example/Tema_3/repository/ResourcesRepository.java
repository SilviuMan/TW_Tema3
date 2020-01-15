package com.example.Tema_3.repository;

import com.example.Tema_3.models.Resources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ResourcesRepository extends CrudRepository<Resources, Long>, JpaRepository<Resources, Long> {
    Resources findResourcesById(Long id);
    Resources findResourcesByName(String name);
}
