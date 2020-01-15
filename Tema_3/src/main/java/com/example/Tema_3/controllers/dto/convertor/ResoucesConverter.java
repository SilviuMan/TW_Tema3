package com.example.Tema_3.controllers.dto.convertor;

import com.example.Tema_3.controllers.dto.ResourcesDto;
import com.example.Tema_3.models.Resources;

public class ResoucesConverter {
    public static ResourcesDto of(Resources resources){
        ResourcesDto resourcesDto=new ResourcesDto(resources.getId(),resources.getName(),resources.getText());
        resourcesDto.setVersion(resources.getVersion());
        return  resourcesDto;
    }
    public static Resources of(ResourcesDto resources){
        Resources resourcesDto=new Resources(resources.getId(),resources.getName(),resources.getText());
        resourcesDto.setVersion(resources.getVersion());
        return  resourcesDto;
    }
}
