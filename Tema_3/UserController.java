package com.example.tema3.tema3.controllers;

import com.example.tema3.tema3.models.Resources;
import com.example.tema3.tema3.models.Rights;
import com.example.tema3.tema3.models.Roles;
import com.example.tema3.tema3.models.Users;
import com.example.tema3.tema3.repository.RolesRepository;
import com.example.tema3.tema3.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;

@RestController
public class UserController {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    RolesRepository rolesRepository;

    @RequestMapping(value = "/user")
    public String so(){
        Users myUser = new Users("ana","ana");

//        myUser.setRoles(rolesSet);


        Rights myRights = new Rights("write");
        Rights myRights2 = new Rights("read");

        Resources myResources = new Resources("resursa1","text");
        Resources myResources2 = new Resources("resursa2","text");

        Set<Rights> rightsSet = new HashSet<>();
        Set<Resources> resourcesSet = new HashSet<>();

        rightsSet.add(myRights);
        rightsSet.add(myRights2);

        resourcesSet.add(myResources);
        resourcesSet.add(myResources2);

        Roles roles = new Roles();
        Roles roles1 = new Roles();

        roles.setResources(resourcesSet);
        roles1.setRights(rightsSet);

        roles1.setResources(resourcesSet);

        Set<Roles> rolesSet = new HashSet<>();
        rolesSet.add(roles);
        rolesSet.add(roles1);

        myUser.setRoles(rolesSet);
        System.out.println(myUser.toString());

        usersRepository.save(myUser);
        System.out.println(myUser);
        return  "";
    }



}
