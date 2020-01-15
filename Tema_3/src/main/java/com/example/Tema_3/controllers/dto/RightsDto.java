package com.example.Tema_3.controllers.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class RightsDto {

    private Long id;

    private String name;

    public RightsDto() {
    }
    public RightsDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public RightsDto( String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Rights{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
