package com.holoview.holoview.service.impl;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.holoview.holoview.controller.dto.shopArragement.InShopArrangementDTO;
import com.holoview.holoview.model.entity.Shop;
import com.holoview.holoview.model.entity.ShopArrangement;
import com.holoview.holoview.model.repository.ShopArrangementRepository;
import com.holoview.holoview.service.IShopArrangementService;
import com.holoview.holoview.service.exception.NotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ShopArrangementService implements IShopArrangementService {
    private final ShopArrangementRepository repository;
    private final ShopService shopService;

    @Override
    public ShopArrangement create(InShopArrangementDTO dto) {
        Shop shopFound = shopService.findById(dto.shopId());
        ShopArrangement newShopArrangement = new ShopArrangement();

        BeanUtils.copyProperties(dto, newShopArrangement);
        newShopArrangement.setShop(shopFound);

        return repository.save(newShopArrangement);
    }

    @Override
    public ShopArrangement update(UUID id, InShopArrangementDTO dto) {
        ShopArrangement shopArrangementFound = repository.findById(id).orElseThrow(NotFoundException::new);

        BeanUtils.copyProperties(dto, shopArrangementFound);

        return repository.save(shopArrangementFound);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new NotFoundException();

        repository.deleteById(id);
    }
}