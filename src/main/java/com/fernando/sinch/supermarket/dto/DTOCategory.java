package com.fernando.sinch.supermarket.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DTOCategory implements Serializable {
    private Integer id;
    private String name;

    public DTOCategory() {

    }

    public DTOCategory(String name) {
        this.name = name;
    }
}
