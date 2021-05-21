package com.fernando.sinch.supermarket.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "category")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    public Category() {

    }

    public Category(String name) {
        this.name = name;
    }
}
