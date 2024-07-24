package com.holoview.holoview.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.holoview.holoview.controller.dto.inactiveSquare.InInactiveSquareDTO;
import com.holoview.holoview.controller.dto.inactiveSquare.InInactiveSquareListDTO;
import com.holoview.holoview.model.entity.InactiveSquare;
import com.holoview.holoview.model.entity.ShopArrangement;
import com.holoview.holoview.model.repository.InactiveSquareRepository;
import com.holoview.holoview.service.IInactiveSquareService;
import com.holoview.holoview.service.exception.NotFoundException;
import com.holoview.holoview.util.Validation;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InactiveSquareService implements IInactiveSquareService {
    private final InactiveSquareRepository repository;
    private final ShopArrangementService shopArrangementService;
    private final Validation validation;

    // POST
    @Override
    public InactiveSquare create(InInactiveSquareDTO dto) {
        ShopArrangement shopArrangementFound = shopArrangementService.findById(dto.shopArrangementId());

        this.validation.validateSquareLocalization(dto.x(), dto.y(), shopArrangementFound);

        InactiveSquare newInactiveSquare = new InactiveSquare();

        BeanUtils.copyProperties(dto, newInactiveSquare);
        newInactiveSquare.setArrangement(shopArrangementFound);

        return repository.save(newInactiveSquare);
    }

    @Override
    public void createList(InInactiveSquareListDTO dto) {
        ShopArrangement shopArrangementFound = shopArrangementService.findById(dto.shopArrangementId());

        List<InactiveSquare> inactiveSquares = new ArrayList<>();

        dto.inactiveSquares().stream()
                .forEach(inactiveSquare -> {
                    this.validation.validateSquareLocalization(inactiveSquare.x(), inactiveSquare.y(), shopArrangementFound);

                    InactiveSquare newInactiveSquare = new InactiveSquare();

                    newInactiveSquare.setX(inactiveSquare.x());
                    newInactiveSquare.setY(inactiveSquare.y());
                    newInactiveSquare.setArrangement(shopArrangementFound);

                    inactiveSquares.add(newInactiveSquare);
                });

        repository.saveAll(inactiveSquares);
    }

    // DELETE
    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new NotFoundException();

        repository.deleteById(id);
    }
}