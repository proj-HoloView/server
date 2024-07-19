package com.holoview.holoview.controller.dto.shelf;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record InShelfDTO(@NotNull @Min(0) Integer x, @NotNull @Min(0) Integer y, UUID shopArrangementId) {

}