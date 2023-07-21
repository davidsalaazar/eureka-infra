package com.experience.scloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.experience.scloud.model.ProductModel;
import com.experience.scloud.model.response.ProductsResponse;
import com.experience.scloud.service.IProductService;

/**
 * @author: SALAZAR
 * @date: 30 may. 2020
 * @time: 12:33:35
 */
@RestController
public class ProductController {

	@Autowired
	private IProductService productService;

	@GetMapping("/api/get-list")
	public ResponseEntity<ProductsResponse> getList() {
		return this.productService.getAll();
	}

	@GetMapping("/api/get/{id}")
	public ResponseEntity<ProductModel> getProduct(@PathVariable long id) {
		return this.productService.getOne(id);
	}

}