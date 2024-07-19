package com.holoview.holoview.service.impl;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.holoview.holoview.controller.dto.shelfProduct.InShelfProductDTO;
import com.holoview.holoview.model.entity.Product;
import com.holoview.holoview.model.entity.Shelf;
import com.holoview.holoview.model.entity.ShelfProduct;
import com.holoview.holoview.model.repository.ShelfProductRepository;
import com.holoview.holoview.service.IShelfProductService;
import com.holoview.holoview.service.exception.NotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ShelfProductService implements IShelfProductService {
    private final ShelfProductRepository repository;
    private final ProductService productService;
    private final ShelfService shelfService;

    @Override
    public ShelfProduct create(InShelfProductDTO dto) {
        Shelf shelfFound = shelfService.findById(dto.shelfId());
        Product productFound = productService.findById(dto.productId());

        ShelfProduct newShelfProduct = new ShelfProduct();

        BeanUtils.copyProperties(dto, newShelfProduct);
        newShelfProduct.setProduct(productFound);
        newShelfProduct.setShelf(shelfFound);

        return repository.save(newShelfProduct);
    }

    public ShelfProduct findById(UUID id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new NotFoundException();

        repository.deleteById(id);
    }
}