package com.holoview.holoview.controller.dto.product;

import java.util.UUID;

import com.holoview.holoview.model.entity.Product;

public record OutProductDTO(UUID id, String name, String description, Double price, Integer amount, String picture) {
    public OutProductDTO(Product p) {
        this(p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getAmount(), p.getPicture());
    }
}
