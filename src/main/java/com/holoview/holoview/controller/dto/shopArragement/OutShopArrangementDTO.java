package com.holoview.holoview.controller.dto.shopArragement;

import java.util.UUID;

import com.holoview.holoview.model.entity.ShopArrangement;

public record OutShopArrangementDTO(UUID id, Integer sideSize) {
    public OutShopArrangementDTO(ShopArrangement sa) {
        this(sa.getId(), sa.getSideSize());
    }
}