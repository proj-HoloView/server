package com.holoview.holoview.controller.dto.shop;

import java.util.UUID;

import com.holoview.holoview.model.entity.Shop;

public record OutShopDTO(UUID id, String name, Boolean active) {
    public OutShopDTO(Shop s) {
        this(s.getId(), s.getName(), s.getActive());
    }
}
