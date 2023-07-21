package com.experience.scloud.service.impl;

import com.experience.scloud.model.Item;
import com.experience.scloud.model.ProductModel;
import com.experience.scloud.model.response.ItemsResponse;
import com.experience.scloud.model.response.ProductsResponse;
import com.experience.scloud.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author: SALAZAR
 * @date: 30 may. 2020
 * @time: 14:49:28
 */
@Service
public class ItemService implements IItemService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service.products.get-list}")
    private String productsGetList;

    @Value("${service.products.get-one}")
    private String productsGetOne;

    @Override
    public ResponseEntity<ItemsResponse> getAll() {
        try {
            ItemsResponse itemResponse = new ItemsResponse();
            ResponseEntity<ProductsResponse> response = this.restTemplate.getForEntity(this.productsGetList, ProductsResponse.class);

            itemResponse.setItems(response.getBody().getProducts().stream().map(p -> new Item(p, UUID.randomUUID().variant())).collect(Collectors.toList()));
            return new ResponseEntity<>(itemResponse, HttpStatus.OK);
        } catch (HttpStatusCodeException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @Override
    public ResponseEntity<Item> getOne(Long id, int quantity) {
        try {
            ResponseEntity<ProductModel> response = this.restTemplate.getForEntity(this.productsGetOne + id, ProductModel.class);
            return new ResponseEntity<>(new Item(response.getBody(), quantity), HttpStatus.OK);
        } catch (HttpStatusCodeException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
}
