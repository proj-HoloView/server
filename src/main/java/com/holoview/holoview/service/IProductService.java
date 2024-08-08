package com.holoview.holoview.service;

import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.holoview.holoview.controller.dto.product.InProductDTO;
import com.holoview.holoview.model.entity.Product;

public interface IProductService extends
                IWritable<Product, UUID, InProductDTO>,
                IReadable<Product, UUID>,
                IEditable<Product, UUID, InProductDTO> {
        List<Product> findAllByShopId(UUID shopId);

        Product updatePrice(UUID id, Double newPrice);

        Product updateAmount(UUID id, Integer newAmount);

        Product updatePicture(UUID id, MultipartFile picture);
}