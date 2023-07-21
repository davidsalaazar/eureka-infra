package com.experience.scloud.model.response;

import com.experience.scloud.model.Item;
import lombok.Data;

import java.util.List;

/**
 * @author: SALAZAR
 * @date: 30 may. 2020
 * @time: 14:18:09
 */
@Data
public class ItemsResponse {

    private List<Item> items;
}
