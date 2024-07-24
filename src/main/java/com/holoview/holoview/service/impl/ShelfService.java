package com.holoview.holoview.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.holoview.holoview.controller.dto.shelf.InShelfDTO;
import com.holoview.holoview.controller.dto.shelf.InShelfListDTO;
import com.holoview.holoview.model.entity.Shelf;
import com.holoview.holoview.model.entity.ShopArrangement;
import com.holoview.holoview.model.repository.ShelfRepository;
import com.holoview.holoview.service.IShelfService;
import com.holoview.holoview.service.exception.NotFoundException;
import com.holoview.holoview.util.Validation;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ShelfService implements IShelfService {
    private final ShelfRepository repository;
    private final ShopArrangementService shopArrangementService;
    private final Validation validation;

    // POST
    @Override
    public Shelf create(InShelfDTO dto) {
        ShopArrangement shopArrangementFound = shopArrangementService.findById(dto.shopArrangementId());

        this.validation.validateSquareLocalization(dto.x(), dto.y(), shopArrangementFound);

        Shelf newShelf = new Shelf();

        BeanUtils.copyProperties(dto, newShelf);
        newShelf.setArrangement(shopArrangementFound);

        return repository.save(newShelf);
    }

    @Override
    public void createShelfList(InShelfListDTO dto) {
        ShopArrangement shopArrangementFound = shopArrangementService.findById(dto.shopArrangementId());

        List<Shelf> shelfs = new ArrayList<>();

        dto.shelfs().stream()
                .forEach(shelf -> {
                    this.validation.validateSquareLocalization(shelf.x(), shelf.y(), shopArrangementFound);

                    Shelf newShelf = new Shelf();

                    newShelf.setX(shelf.x());
                    newShelf.setY(shelf.y());
                    newShelf.setArrangement(shopArrangementFound);

                    shelfs.add(newShelf);
                });

        repository.saveAll(shelfs);
    }

    // DELETE
    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new NotFoundException();

        repository.deleteById(id);
    }

    // UTIL
    public Shelf findById(UUID id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }
}