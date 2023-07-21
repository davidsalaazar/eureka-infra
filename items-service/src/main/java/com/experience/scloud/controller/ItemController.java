package com.experience.scloud.controller;

import com.experience.scloud.model.Item;
import com.experience.scloud.model.response.ItemsResponse;
import com.experience.scloud.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author: SALAZAR
 * @date: 30 may. 2020
 * @time: 15:37:19
 */
@RefreshScope
@RestController
public class ItemController {

    @Autowired
    private IItemService itemService;

    @GetMapping("/api/get-list")
    public ResponseEntity<ItemsResponse> getList() {
        return this.itemService.getAll();
    }

    @GetMapping("/api/get/{id}")
    public ResponseEntity<Item> getOne(@PathVariable Long id) {
        return this.itemService.getOne(id, UUID.randomUUID().variant());
    }
}