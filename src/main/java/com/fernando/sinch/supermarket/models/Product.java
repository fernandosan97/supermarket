package com.fernando.sinch.supermarket.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "stock")
    private Integer stock;

    @OneToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Product() {

    }

    public Product(String description, Double price, Integer stock) {
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
}
