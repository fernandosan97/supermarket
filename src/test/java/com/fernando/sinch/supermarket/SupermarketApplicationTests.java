package com.fernando.sinch.supermarket;

import com.fernando.sinch.supermarket.dto.DTOCategory;
import com.fernando.sinch.supermarket.dto.DTODetail;
import com.fernando.sinch.supermarket.dto.DTOInvoice;
import com.fernando.sinch.supermarket.dto.DTOProduct;
import com.fernando.sinch.supermarket.models.Category;
import com.fernando.sinch.supermarket.models.Product;
import com.fernando.sinch.supermarket.payload.request.InvoiceRequest;
import com.fernando.sinch.supermarket.services.CategoryServiceImpl;
import com.fernando.sinch.supermarket.services.InvoiceServiceImpl;
import com.fernando.sinch.supermarket.services.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class SupermarketApplicationTests {
	CategoryServiceImpl categoryService;
	ProductServiceImpl productService;
	InvoiceServiceImpl invoiceService;

	@Autowired
	public SupermarketApplicationTests(CategoryServiceImpl categoryService, ProductServiceImpl productService, InvoiceServiceImpl invoiceService) {
		this.categoryService = categoryService;
		this.productService = productService;
		this.invoiceService = invoiceService;
	}

	@Test
	@DisplayName("Should create a new Category")
	void createCategory() {
		// Get quantity of categories
		int cantCategories = categoryService.getCategories().size();

		// Create new category
		Category category = new Category("Hogar");
		DTOCategory dtoCategory = new DTOCategory(category.getName());
		categoryService.saveOrUpdate(dtoCategory);

		// Get quantity of categories again
		int newCantCategories = categoryService.getCategories().size();

		Assertions.assertTrue(newCantCategories > cantCategories);
	}

	@Test
	@DisplayName("Should return a Category")
	void getCategory() {
		// Get category
		DTOCategory category = categoryService.getById(1);
		Assertions.assertNotNull(category);
	}

	@Test
	@DisplayName("Should create a new Product")
	void createProduct() {
		// Get quantity of products
		int cantProducts = productService.getProducts().size();

		// Get Category
		DTOCategory category = categoryService.getById(1);
		Category objCategory = new Category();
		objCategory.setId(category.getId());
		objCategory.setName(category.getName());

		// Create new Product
		DTOProduct dtoProduct = new DTOProduct();
		dtoProduct.setDescription("Plancha");
		dtoProduct.setPrice(450.00);
		dtoProduct.setStock(10);
		dtoProduct.setCategory(objCategory);

		productService.saveOrUpdate(dtoProduct);

		// Get quantity of products again
		int newCantProducts = productService.getProducts().size();

		Assertions.assertTrue(newCantProducts > cantProducts);
	}

	@Test
	@DisplayName("Should return a Product")
	void getProduct() {
		// Get product
		Product product = productService.getById(1).get();
		Assertions.assertNotNull(product);
	}

	@Test
	@DisplayName("Should create an invoice")
	void createInvoice() {
		// Get quantity of invoices
		int cantInvoices = invoiceService.getInvoices().size();

		// Create Invoice Request
		InvoiceRequest invoiceRequest = new InvoiceRequest();
		invoiceRequest.setDescription("CONSUMIDOR FINAL/FERNANDO SANCHEZ");

		Set<DTODetail> setDtoDetail = new HashSet<DTODetail>();
		DTODetail dtoDetail = new DTODetail();
		dtoDetail.setIdProduct(5);
		dtoDetail.setQuantity(4);
		setDtoDetail.add(dtoDetail);

		DTODetail dtoDetail2 = new DTODetail();
		dtoDetail2.setIdProduct(13);
		dtoDetail2.setQuantity(5);
		setDtoDetail.add(dtoDetail2);

		invoiceRequest.setDetail(setDtoDetail);

		DTOInvoice dtoInvoice = invoiceService.saveOrUpdate(invoiceRequest);

		// Get quantity of invoices again
		int newCantInvoices = invoiceService.getInvoices().size();

		Assertions.assertTrue(newCantInvoices > cantInvoices);
	}
}
