package com.holoview.holoview.controller.dto.promotion;

import java.time.LocalDateTime;
import java.util.UUID;

import com.holoview.holoview.model.entity.Promotion;

public record OutPromotionDTO(UUID id, LocalDateTime inicialDate, LocalDateTime finalDate, Double finalPrice) {
    public OutPromotionDTO(Promotion p) {
        this(p.getId(), p.getInicialDate(), p.getFinalDate(), p.getFinalPrice());
    }
}