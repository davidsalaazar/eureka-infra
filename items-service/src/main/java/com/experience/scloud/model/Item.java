package com.experience.scloud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: SALAZAR
 * @date: 30 may. 2020
 * @time: 14:38:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private ProductModel product;
    private int quantity;
}
