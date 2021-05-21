package com.fernando.sinch.supermarket.services;

import com.fernando.sinch.supermarket.dto.DTODetail;
import com.fernando.sinch.supermarket.dto.DTOInvoice;
import com.fernando.sinch.supermarket.models.Invoice;
import com.fernando.sinch.supermarket.payload.request.InvoiceRequest;

import java.util.List;

public interface IInvoice {
    List<Invoice> getInvoices();

    DTOInvoice saveOrUpdate(InvoiceRequest invoiceRequest);

    List<DTODetail> validStock(InvoiceRequest invoiceRequest);

    DTOInvoice getById(Integer id);
}
