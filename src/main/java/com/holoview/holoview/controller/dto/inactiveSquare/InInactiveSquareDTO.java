package com.holoview.holoview.controller.dto.inactiveSquare;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record InInactiveSquareDTO(@Min(0) @NotNull Integer x, @Min(0) @NotNull Integer y, UUID shopArrangementId) {
    
}