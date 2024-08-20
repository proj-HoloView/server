package com.holoview.holoview.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.holoview.holoview.controller.dto.shop.InShopDTO;
import com.holoview.holoview.model.entity.Shop;
import com.holoview.holoview.model.repository.ShopRepository;
import com.holoview.holoview.service.IShopService;
import com.holoview.holoview.service.exception.BadRequestException;
import com.holoview.holoview.service.exception.NotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ShopService implements IShopService {
    private final ShopRepository repository;

    @Override
    public Shop create(InShopDTO dto) {
        if (repository.findByName(dto.name()).isPresent())
            throw new BadRequestException("Nome já utilizado");

        Shop newShop = new Shop();

        BeanUtils.copyProperties(dto, newShop);
        newShop.setActive(true);

        return repository.save(newShop);
    }

    @Override
    public Shop findById(UUID id) {
        Shop shopFound = repository.findById(id).orElseThrow(NotFoundException::new);

        return shopFound;
    }

    public Boolean existsByName(String name) {
        return repository.findByName(name).isPresent();
    }

    @Override
    public List<Shop> findAll() {
        List<Shop> shopsFound = repository.findAll();

        return shopsFound;
    }

    @Override
    public Shop update(UUID id, InShopDTO dto) {
        Shop shopFound = findById(id);

        BeanUtils.copyProperties(dto, shopFound);

        return repository.save(shopFound);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new NotFoundException();

        repository.deleteById(id);
    }
}