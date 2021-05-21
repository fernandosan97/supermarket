package com.fernando.sinch.supermarket.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "kardex")
public class Kardex implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date", nullable = false)
    private Date date;

    @OneToOne
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public Kardex() {

    }

    public Kardex(Date date, Invoice invoice, Product product, Integer quantity) {
        this.date = date;
        this.invoice = invoice;
        this.product = product;
        this.quantity = quantity;
    }
}
