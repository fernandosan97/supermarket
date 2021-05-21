package com.fernando.sinch.supermarket.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "detail")
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detail")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public Detail() {

    }

    public Detail(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
