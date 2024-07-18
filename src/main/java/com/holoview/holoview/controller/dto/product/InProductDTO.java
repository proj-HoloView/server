package com.holoview.holoview.controller.dto.product;

import java.util.UUID;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record InProductDTO(@NotNull @NotBlank @Size(min = 3) String name,
        @Size(max = 255) String description,
        @NotNull @DecimalMin("0.0") Double price,
        @NotNull @Min(0) Integer amount,
        UUID categoryId,
        UUID shopId) {

}