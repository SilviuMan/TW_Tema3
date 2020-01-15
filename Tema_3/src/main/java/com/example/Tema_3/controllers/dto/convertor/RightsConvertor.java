package com.example.Tema_3.controllers.dto.convertor;

import com.example.Tema_3.controllers.dto.RightsDto;
import com.example.Tema_3.models.Rights;

public class RightsConvertor {
    public static RightsDto of(Rights rights){
        RightsDto rightsDto=new RightsDto(rights.getId(),rights.getName());
        return rightsDto;
    }
    public static Rights of(RightsDto rights){
        Rights rightsDto=new Rights(rights.getId(),rights.getName());
        return rightsDto;
    }
}
