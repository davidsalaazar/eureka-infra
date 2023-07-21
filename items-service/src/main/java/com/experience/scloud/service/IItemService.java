package com.experience.scloud.service;

import com.experience.scloud.model.Item;
import com.experience.scloud.model.response.ItemsResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author: SALAZAR
 * @date: 30 may. 2020
 * @time: 14:49:55
 */
public interface IItemService {
    ResponseEntity<ItemsResponse> getAll();

    ResponseEntity<Item> getOne(Long id, int quantity);
}