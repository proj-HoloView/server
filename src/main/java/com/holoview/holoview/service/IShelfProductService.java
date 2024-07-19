package com.holoview.holoview.service;

import java.util.UUID;

import com.holoview.holoview.controller.dto.shelfProduct.InShelfProductDTO;
import com.holoview.holoview.model.entity.ShelfProduct;

public interface IShelfProductService extends
        IWritable<ShelfProduct, UUID, InShelfProductDTO> {

}