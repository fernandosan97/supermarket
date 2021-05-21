package com.fernando.sinch.supermarket.services;

import com.fernando.sinch.supermarket.dto.DTOCategory;
import com.fernando.sinch.supermarket.dto.DTOProduct;
import com.fernando.sinch.supermarket.models.Category;
import com.fernando.sinch.supermarket.models.Product;
import com.fernando.sinch.supermarket.repository.CategoryRepository;
import com.fernando.sinch.supermarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProduct{
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ICategory iCategory;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ICategory iCategory) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.iCategory = iCategory;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public DTOProduct saveOrUpdate(DTOProduct dtoProduct) {
        // Searh dtoCategory
        DTOCategory dtoCategory = iCategory.getById(dtoProduct.getCategory().getId());

        // Parse to Category
        Category category = new Category();
        category.setId(dtoCategory.getId());
        category.setName(dtoCategory.getName());

        // Parse dtoProduct to Product
        Product objProduct = new Product(dtoProduct.getDescription(), dtoProduct.getPrice(), dtoProduct.getStock());
        objProduct.setCategory(category);

        // Save Product
        Product newProduct = productRepository.save(objProduct);

        // new DTOProduct
        DTOProduct productDTO = new DTOProduct();
        productDTO.setId(newProduct.getId());
        productDTO.setDescription(newProduct.getDescription());
        productDTO.setPrice(newProduct.getPrice());
        productDTO.setStock(newProduct.getStock());
        productDTO.setCategory(newProduct.getCategory());

        return productDTO;
    }

    @Override
    public Optional<Product> getById(Integer id) {
        return productRepository.findById(id);
    }
}
