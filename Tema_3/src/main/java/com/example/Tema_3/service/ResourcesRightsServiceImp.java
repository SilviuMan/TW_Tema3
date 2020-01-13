package com.example.Tema_3.service;

import com.example.Tema_3.models.ResourcesRights;
import com.example.Tema_3.models.Users;
import com.example.Tema_3.repository.ResourcesRightsRepository;
import com.example.Tema_3.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class ResourcesRightsServiceImp implements ResourcesRightsService {
    @Autowired
    private ResourcesRightsRepository resourcesRightsRepository;
    @Override
    public List<ResourcesRights> findAll() {
        List<ResourcesRights> result = (List<ResourcesRights>) resourcesRightsRepository.findAll();
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<ResourcesRights>();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
