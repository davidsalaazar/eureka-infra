package com.experience.scloud.model;

import com.google.common.base.MoreObjects;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductModel {

    private Long id;
    private String name;
    private double price;
    private int port;
    private LocalDate created;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("price", price)
                .add("port", port)
                .add("created", created)
                .toString();
    }
}
