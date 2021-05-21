package com.fernando.sinch.supermarket.payload.request;

import com.fernando.sinch.supermarket.dto.DTODetail;
import lombok.Data;

import java.util.Set;

@Data
public class InvoiceRequest {
    private String description;
    private Set<DTODetail> detail;
}
