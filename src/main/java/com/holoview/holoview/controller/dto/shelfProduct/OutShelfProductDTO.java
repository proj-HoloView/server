package com.holoview.holoview.controller.dto.shelfProduct;

import java.util.UUID;

import com.holoview.holoview.model.entity.ShelfProduct;

public record OutShelfProductDTO(UUID id, Integer amount) {
    public OutShelfProductDTO(ShelfProduct sp) {
        this(sp.getId(), sp.getAmount());
    }
}