package com.holoview.holoview.controller.dto.shop;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InShopDTO(@NotBlank @NotNull @Min(6) String name) {

}