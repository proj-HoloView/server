package com.holoview.holoview.service.impl;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.holoview.holoview.controller.dto.inactiveSquare.InInactiveSquareDTO;
import com.holoview.holoview.model.entity.InactiveSquare;
import com.holoview.holoview.model.entity.ShopArrangement;
import com.holoview.holoview.model.repository.InactiveSquareRepository;
import com.holoview.holoview.service.IInactiveSquareService;
import com.holoview.holoview.service.exception.NotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InactiveSquareService implements IInactiveSquareService {
    private final InactiveSquareRepository repository;
    private final ShopArrangementService shopArrangementService;

    @Override
    public InactiveSquare create(InInactiveSquareDTO dto) {
        ShopArrangement shopArrangementFound = shopArrangementService.findById(dto.shopArrangementId());

        InactiveSquare newInactiveSquare = new InactiveSquare();

        BeanUtils.copyProperties(dto, newInactiveSquare);
        newInactiveSquare.setArrangement(shopArrangementFound);

        return repository.save(newInactiveSquare);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new NotFoundException();

        repository.deleteById(id);
    }
}