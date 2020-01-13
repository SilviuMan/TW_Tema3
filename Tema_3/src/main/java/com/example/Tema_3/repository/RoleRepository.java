package com.example.Tema_3.repository;

import com.example.Tema_3.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long>, JpaRepository<Role, Long> {
    Role findRoleById(Long id);
    List<Role> findAll();
}
