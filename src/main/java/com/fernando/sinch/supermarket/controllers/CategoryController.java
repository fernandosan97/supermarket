package com.fernando.sinch.supermarket.controllers;

import com.fernando.sinch.supermarket.dto.DTOCategory;
import com.fernando.sinch.supermarket.json.JsonResponse;
import com.fernando.sinch.supermarket.models.Category;
import com.fernando.sinch.supermarket.services.ICategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    ICategory iCategory;

    @GetMapping()
    @PreAuthorize("hasRole('CASHIER') or hasRole('ADMIN')")
    public JsonResponse getCategories() {
        List<Category> categories = iCategory.getCategories();

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.put("data", categories);
        jsonResponse.setMessage("All categories");
        return jsonResponse;
    }

    @PostMapping()
    @PreAuthorize("hasRole('CASHIER') or hasRole('ADMIN')")
    public JsonResponse saveOrUpdateCategory(@RequestBody final DTOCategory dtoCategory) {
        JsonResponse response = new JsonResponse();

        DTOCategory objDtoCategory = iCategory.saveOrUpdate(dtoCategory);

        response.put("data", objDtoCategory);
        response.setMessage("Register successfully");

        return response;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CASHIER') or hasRole('ADMIN')")
    public JsonResponse findById(@PathVariable("id") Integer id) {
        DTOCategory dtoCategory = iCategory.getById(id);

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.put("data", dtoCategory);
        jsonResponse.setMessage("Data of category requested");
        return jsonResponse;
    }
}
