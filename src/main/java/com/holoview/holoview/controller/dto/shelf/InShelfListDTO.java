package com.holoview.holoview.controller.dto.shelf;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record InShelfListDTO(UUID shopArrangementId, List<SimpleShelf> shelfs) {
    public record SimpleShelf(@Min(0) @NotNull Integer x, @Min(0) @NotNull Integer y) {
    }
}