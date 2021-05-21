package com.fernando.sinch.supermarket.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "detail_invoice")
public class DetailInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

    @OneToOne
    @JoinColumn(name = "detail_id", nullable = false)
    private Detail detail;

    public DetailInvoice() {

    }

    public DetailInvoice(Invoice invoice, Detail detail) {
        this.invoice = invoice;
        this.detail = detail;
    }
}
