package com.fernando.sinch.supermarket.services;

import com.fernando.sinch.supermarket.dto.DTOCategory;
import com.fernando.sinch.supermarket.models.Category;

import java.util.List;

public interface ICategory {
    List<Category> getCategories();

    DTOCategory saveOrUpdate(DTOCategory category);

    DTOCategory getById(Integer id);
}
