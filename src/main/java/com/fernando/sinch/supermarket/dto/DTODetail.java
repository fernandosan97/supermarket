package com.fernando.sinch.supermarket.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DTODetail implements Serializable {
    private Integer id;
    private Integer idProduct;
    private String description;
    private Double price;
    private Integer quantity;

    public DTODetail() {

    }

    public DTODetail(Integer idProduct, String description, Double price, Integer quantity) {
        this.idProduct = idProduct;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
}
