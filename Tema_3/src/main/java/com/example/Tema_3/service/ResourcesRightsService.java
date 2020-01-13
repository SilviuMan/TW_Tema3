package com.example.Tema_3.service;

import com.example.Tema_3.models.ResourcesRights;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface ResourcesRightsService  extends  UserDetailsService{
    List<ResourcesRights> findAll();
}
