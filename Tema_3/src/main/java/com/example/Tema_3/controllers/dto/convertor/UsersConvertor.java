package com.example.Tema_3.controllers.dto.convertor;

import com.example.Tema_3.controllers.dto.UsersDto;
import com.example.Tema_3.models.Users;

public class UsersConvertor {
    public static UsersDto of(Users users){
        UsersDto usersDto=new UsersDto(users.getId(),users.getUsername(),users.getPassword());
        usersDto.setRoles(users.getRole());
        usersDto.setRoles(users.getRoles());
        return usersDto;
    }
    public static Users of(UsersDto users){
        Users usersDto=new Users(users.getId(),users.getUsername(),users.getPassword());
        usersDto.setRoles(users.getRole());
        usersDto.setRoles(users.getRoles());
        return usersDto;
    }
}
