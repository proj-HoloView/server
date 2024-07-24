package com.holoview.holoview.controller.dto.inactiveSquare;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record InInactiveSquareListDTO(UUID shopArrangementId, List<SimpleInactiveSquareDTO> inactiveSquares) {
    public record SimpleInactiveSquareDTO(@Min(0) @NotNull Integer x, @Min(0) @NotNull Integer y) {
    }
}