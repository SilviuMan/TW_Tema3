package com.example.Tema_3.repository;

import com.example.Tema_3.models.Rights;
import com.example.Tema_3.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRoleRepository extends CrudRepository<UserRole, Long>, JpaRepository<UserRole, Long> {
    List<UserRole> findAll();
}
