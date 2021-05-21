package com.fernando.sinch.supermarket.services;

import com.fernando.sinch.supermarket.dto.DTODetail;
import com.fernando.sinch.supermarket.dto.DTOInvoice;

import java.util.List;

public interface IDetailInvoice {
    List<DTODetail> getDetailInvoice(DTOInvoice dtoInvoice);
}
