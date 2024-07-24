package com.holoview.holoview.util;

import org.springframework.stereotype.Component;

import com.holoview.holoview.model.entity.ShopArrangement;
import com.holoview.holoview.model.repository.InactiveSquareRepository;
import com.holoview.holoview.model.repository.ShelfRepository;
import com.holoview.holoview.service.CustomLabel;
import com.holoview.holoview.service.exception.BadRequestException;
import com.holoview.holoview.service.exception.ConflictException;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class Validation {
    private final InactiveSquareRepository inactiveSquareRepository;
    private final ShelfRepository shelfRepository;

    public void validateSquareLocalization(Integer x, Integer y, ShopArrangement sa) {
        if (x > sa.getSideSize() - 1 || y > sa.getSideSize() - 1) {
            throw new BadRequestException(CustomLabel.OUT_RANGED_LOCALIZATION);
        }

        if (inactiveSquareRepository.findByXAndYAndArrangementId(x, y, sa.getId()).isPresent()
                || shelfRepository.findByXAndYAndArrangementId(x, y, sa.getId()).isPresent()) {
            throw new ConflictException();
        }
    }
}
