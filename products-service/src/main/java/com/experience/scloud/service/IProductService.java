package com.experience.scloud.service;

import org.springframework.http.ResponseEntity;

import com.experience.scloud.model.ProductModel;
import com.experience.scloud.model.response.ProductsResponse;

public interface IProductService {

	public ResponseEntity<ProductsResponse> getAll();

	public ResponseEntity<ProductModel> getOne(Long id);

}
