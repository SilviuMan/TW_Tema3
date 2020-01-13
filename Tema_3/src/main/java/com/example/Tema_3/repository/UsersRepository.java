package com.example.Tema_3.repository;


import com.example.Tema_3.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRepository extends CrudRepository<Users, Long>, JpaRepository<Users, Long> {

    Users findUsersByUsername(String username);
    Users findUsersById(Long id);
    List<Users> findAll();
    
    List<Users> findAllByRoles(String roles);


}
