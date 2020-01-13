package com.example.Tema_3.repository;

import com.example.Tema_3.models.Resources;
import com.example.Tema_3.models.ResourcesRights;
import com.example.Tema_3.models.Rights;
import com.example.Tema_3.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RightsRepository extends CrudRepository<Rights, Long>, JpaRepository<Rights, Long> {
    Rights findRightsById(Long id);
}
