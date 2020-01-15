package com.example.Tema_3.controllers.dto.convertor;

import com.example.Tema_3.controllers.dto.RoleDto;
import com.example.Tema_3.models.Role;

import java.util.HashSet;
import java.util.Set;

public class RoleConvertor {
    public static RoleDto of(Role role){
        return new RoleDto().setId(role.getId())
                .setResources(role.getResources())
                .setRights(role.getRights());
    }
    public static Role of(RoleDto roleDto){
        Role rol=new Role(roleDto.getId());
        rol.setResources(roleDto.getResources());
        rol.setRights(roleDto.getRights());
        return rol;
    }
    public static Set<RoleDto> off(Set<Role> roleSet){
        Set<RoleDto> roleDtoSet=new HashSet<>();
        for (Role r: roleSet
        ) {
            roleDtoSet.add(RoleConvertor.of(r));
        }
        return roleDtoSet;
    }
    public static Set<Role> of(Set<RoleDto> roleDto){
        Set<Role> roleDtoSet=new HashSet<>();
        for (RoleDto r: roleDto
        ) {
            roleDtoSet.add(RoleConvertor.of(r));
        }
        return roleDtoSet;
    }
}
