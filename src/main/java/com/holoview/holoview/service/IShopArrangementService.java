package com.holoview.holoview.service;

import java.util.UUID;

import com.holoview.holoview.controller.dto.shopArragement.InShopArrangementDTO;
import com.holoview.holoview.model.entity.ShopArrangement;

public interface IShopArrangementService extends
        IWritable<ShopArrangement, UUID, InShopArrangementDTO>,
        IEditable<ShopArrangement, UUID, InShopArrangementDTO> {
}