package com.fernando.sinch.supermarket.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DTOKardex implements Serializable {
    private Integer id;
    private Date date;
    private Integer idInvoice;
    private String productDescription;
    private Integer quantity;

    public DTOKardex() {

    }

    public DTOKardex(Date date, Integer invoice, String productDescription, Integer quantity) {
        this.date = date;
        this.idInvoice = invoice;
        this.productDescription = productDescription;
        this.quantity = quantity;
    }
}
