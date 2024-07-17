package com.holoview.holoview.controller.dto.productCategory;

import java.util.UUID;

import com.holoview.holoview.model.entity.ProductCategory;

public record OutProductCategoryDTO(UUID Id, String category) {
    public OutProductCategoryDTO(ProductCategory c) {
        this(c.getId(), c.getCategory());
    }
}