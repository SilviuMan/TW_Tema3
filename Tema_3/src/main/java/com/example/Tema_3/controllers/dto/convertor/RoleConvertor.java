package com.example.Tema_3.controllers.dto.convertor;

import com.example.Tema_3.controllers.dto.RoleDto;
import com.example.Tema_3.models.Role;

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
}
