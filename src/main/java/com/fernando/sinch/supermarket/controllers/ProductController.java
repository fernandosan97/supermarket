package com.fernando.sinch.supermarket.controllers;

import com.fernando.sinch.supermarket.dto.DTOProduct;
import com.fernando.sinch.supermarket.exception.ResourceNotFoundException;
import com.fernando.sinch.supermarket.json.JsonResponse;
import com.fernando.sinch.supermarket.models.Product;
import com.fernando.sinch.supermarket.services.ICategory;
import com.fernando.sinch.supermarket.services.IProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    IProduct iProduct;

    @Autowired
    ICategory iCategory;

    @GetMapping()
    @PreAuthorize("hasRole('CASHIER') or hasRole('ADMIN')")
    public JsonResponse getProducts() {
        List<Product> products = iProduct.getProducts();

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.put("data", products);
        jsonResponse.setMessage("All products");
        return jsonResponse;
    }

    @PostMapping()
    @PreAuthorize("hasRole('CASHIER') or hasRole('ADMIN')")
    public JsonResponse saveOrUpdateProduct(@RequestBody DTOProduct dtoProduct) {
        DTOProduct objProduct = iProduct.saveOrUpdate(dtoProduct);

        JsonResponse response = new JsonResponse();
        response.put("data", objProduct);
        response.setMessage("Register successfully");

        return response;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CASHIER') or hasRole('ADMIN')")
    public JsonResponse findById(@PathVariable("id") Integer id) {
        Product objProduct = iProduct.getById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Product with id = " + id));

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.put("data", objProduct);
        jsonResponse.setMessage("Data of products requested");
        return jsonResponse;
    }
}
