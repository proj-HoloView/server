package com.holoview.holoview.service.impl;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.holoview.holoview.controller.dto.shelf.InShelfDTO;
import com.holoview.holoview.model.entity.Shelf;
import com.holoview.holoview.model.entity.ShopArrangement;
import com.holoview.holoview.model.repository.ShelfRepository;
import com.holoview.holoview.service.IShelfService;
import com.holoview.holoview.service.exception.NotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ShelfService implements IShelfService {
    private final ShelfRepository repository;
    private final ShopArrangementService shopArrangementService;

    @Override
    public Shelf create(InShelfDTO dto) {
        ShopArrangement shopArrangementFound = shopArrangementService.findById(dto.shopArrangementId());
        Shelf newShelf = new Shelf();

        BeanUtils.copyProperties(dto, newShelf);
        newShelf.setArrangement(shopArrangementFound);

        return repository.save(newShelf);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new NotFoundException();

        repository.deleteById(id);
    }

    public Shelf findById(UUID id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }
}