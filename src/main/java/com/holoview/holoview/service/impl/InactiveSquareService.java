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
import com.holoview.holoview.service.CustomLabel;
import com.holoview.holoview.service.IInactiveSquareService;
import com.holoview.holoview.service.exception.BadRequestException;
import com.holoview.holoview.service.exception.ConflictException;
import com.holoview.holoview.service.exception.NotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InactiveSquareService implements IInactiveSquareService {
    private final InactiveSquareRepository repository;
    private final ShopArrangementService shopArrangementService;

    // POST
    @Override
    public InactiveSquare create(InInactiveSquareDTO dto) {
        ShopArrangement shopArrangementFound = shopArrangementService.findById(dto.shopArrangementId());

        this.validateSquareLocalization(shopArrangementFound, dto.x(), dto.y());

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
                    this.validateSquareLocalization(shopArrangementFound, inactiveSquare.x(), inactiveSquare.y());

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

    // UTIL
    void validateSquareLocalization(ShopArrangement sa, Integer x, Integer y) {
        if (x > sa.getSideSize() - 1 || y > sa.getSideSize() - 1) {
            throw new BadRequestException(CustomLabel.OUT_RANGED_LOCALIZATION);
        }

        if (repository.findByXAndYAndArrangementId(x, y, sa.getId()).isPresent()) {
            throw new ConflictException();
        }
    }
}