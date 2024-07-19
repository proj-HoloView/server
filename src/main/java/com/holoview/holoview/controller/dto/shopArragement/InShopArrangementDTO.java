package com.holoview.holoview.controller.dto.shopArragement;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InShopArrangementDTO(@NotNull @NotBlank @Min(10) Integer sideSize, UUID shopId) {
    
}