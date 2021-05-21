package com.fernando.sinch.supermarket.services;

import com.fernando.sinch.supermarket.dto.DTOProduct;
import com.fernando.sinch.supermarket.models.Product;

import java.util.List;
import java.util.Optional;

public interface IProduct {
    List<Product> getProducts();

    DTOProduct saveOrUpdate(DTOProduct dtoProduct);

    Optional<Product> getById(Integer id);
}
