package com.experience.scloud.dao;

import org.springframework.data.repository.CrudRepository;

import com.experience.scloud.model.ProductModel;

public interface ProductDao extends CrudRepository<ProductModel, Long> {

}
