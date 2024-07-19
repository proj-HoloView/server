package com.holoview.holoview.controller.dto.promotion;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;

public record InPromotionDTO(@FutureOrPresent LocalDateTime inicialDate,
                @Future LocalDateTime finalDate,
                @DecimalMin("0.0") Double finalPrice,
                UUID productId) {
}