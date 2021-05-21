package com.fernando.sinch.supermarket.payload.response;

import lombok.Data;

@Data
public class ReportResponse {
    private Integer id;
    private String description;
    private Integer quantity;

    public ReportResponse() {

    }

    public ReportResponse(Integer idProduct, String description, Integer quantity) {
        this.id = idProduct;
        this.description = description;
        this.quantity = quantity;
    }
}
