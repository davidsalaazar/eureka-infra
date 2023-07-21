package com.experience.scloud.model.response;

import java.util.List;

import com.experience.scloud.model.ProductModel;

import lombok.Data;

/**
 * @author: SALAZAR
 * @date: 30 may. 2020
 * @time: 14:18:09
 */
@Data
public class ProductsResponse {

	private List<ProductModel> products;

}
