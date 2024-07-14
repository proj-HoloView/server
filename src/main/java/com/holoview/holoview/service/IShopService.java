package com.holoview.holoview.service;

import java.util.UUID;

import com.holoview.holoview.controller.dto.shop.InShopDTO;
import com.holoview.holoview.model.entity.Shop;

public interface IShopService extends
        IWritable<Shop, UUID, InShopDTO>,
        IEditable<Shop, UUID, InShopDTO>,
        IReadable<Shop, UUID> {

}
