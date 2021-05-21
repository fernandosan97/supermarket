package com.fernando.sinch.supermarket.dto;

import com.fernando.sinch.supermarket.models.Category;
import lombok.Data;

import java.io.Serializable;


@Data
public class DTOProduct implements Serializable {
    private Integer id;
    private String description;
    private Double price;
    private Integer stock;
    private Category category;

    public DTOProduct() {

    }

    public DTOProduct(String description, Double price, Integer stock) {
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
}
