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
import com.holoview.holoview.service.CustomLabel;
import com.holoview.holoview.service.IShelfService;
import com.holoview.holoview.service.exception.BadRequestException;
import com.holoview.holoview.service.exception.ConflictException;
import com.holoview.holoview.service.exception.NotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ShelfService implements IShelfService {
    private final ShelfRepository repository;
    private final ShopArrangementService shopArrangementService;

    // POST
    @Override
    public Shelf create(InShelfDTO dto) {
        ShopArrangement shopArrangementFound = shopArrangementService.findById(dto.shopArrangementId());

        this.validateShelfLocalization(shopArrangementFound, dto.x(), dto.y());

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
                    this.validateShelfLocalization(shopArrangementFound, shelf.x(), shelf.y());

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

    void validateShelfLocalization(ShopArrangement sa, Integer x, Integer y) {
        if (x > sa.getSideSize() - 1 || y > sa.getSideSize() - 1) {
            throw new BadRequestException(CustomLabel.OUT_RANGED_LOCALIZATION);
        }

        if (repository.findByXAndYAndArrangementId(x, y, sa.getId()).isPresent()) {
            throw new ConflictException();
        }
    }
}