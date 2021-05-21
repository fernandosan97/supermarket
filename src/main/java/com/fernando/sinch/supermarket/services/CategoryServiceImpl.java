package com.fernando.sinch.supermarket.services;

import com.fernando.sinch.supermarket.dto.DTOCategory;
import com.fernando.sinch.supermarket.models.Category;
import com.fernando.sinch.supermarket.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategory{
    CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public DTOCategory saveOrUpdate(DTOCategory dtoCategory) {
        // Parse to Category
        Category category = new Category();
        category.setName(dtoCategory.getName());

        // Save new Category
        Category objCategory = categoryRepository.save(category);

        // New instance of DTOCategory with new Category
        DTOCategory objDtoCategory = new DTOCategory();
        objDtoCategory.setId(objCategory.getId());
        objDtoCategory.setName(objCategory.getName());

        return objDtoCategory;
    }

    @Override
    public DTOCategory getById(Integer id) {
        Category category = categoryRepository.findById(id).get();

        DTOCategory dtoCategory = new DTOCategory();
        dtoCategory.setId(category.getId());
        dtoCategory.setName(category.getName());

        return dtoCategory;
    }
}
