package com.fernando.sinch.supermarket.services;

import com.fernando.sinch.supermarket.dto.DTOKardex;

import java.util.List;
import java.util.Map;

public interface IKardex {
    List<DTOKardex> getKardex();
    List<DTOKardex> getByProduct(Integer idProduct);
    List<Map<String,Object>> getReport();
}
