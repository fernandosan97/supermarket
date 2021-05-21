package com.fernando.sinch.supermarket.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_invoice")
    private Integer id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "subtotal", nullable = false)
    private Double subtotal;

    @Column(name = "isv", nullable = false)
    private Double isv;

    @Column(name = "total", nullable = false)
    private Double total;

    public Invoice() {

    }

    public Invoice(String description, Double subtotal, Double isv, Double total) {
        this.description = description;
        this.subtotal = subtotal;
        this.isv = isv;
        this.total = total;
    }
}
