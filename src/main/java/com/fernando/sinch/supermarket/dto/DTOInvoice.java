package com.fernando.sinch.supermarket.dto;

import com.fernando.sinch.supermarket.models.Invoice;
import lombok.Data;

import java.io.Serializable;

@Data
public class DTOInvoice implements Serializable {
    private Integer id;
    private String description;
    private Double subtotal;
    private Double isv;
    private Double total;

    public DTOInvoice() {

    }

    public DTOInvoice(Invoice invoice) {
        this.id = invoice.getId();
        this.description = invoice.getDescription();
        this.subtotal = invoice.getSubtotal();
        this.isv = invoice.getIsv();
        this.total = invoice.getTotal();
    }
}
