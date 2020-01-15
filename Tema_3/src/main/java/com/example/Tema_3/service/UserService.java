package com.example.Tema_3.service;


import com.example.Tema_3.controllers.dto.UserRegistrationDto;
import com.example.Tema_3.models.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    Users findUsersByUsername(String username);
    Users getUserById(Long id);
    List<Users> findAll(String roles);

    Users save(UserRegistrationDto registration);
}
