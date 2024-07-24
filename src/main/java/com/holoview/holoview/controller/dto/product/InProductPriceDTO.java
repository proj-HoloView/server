package com.holoview.holoview.controller.dto.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record InProductPriceDTO(@NotNull @DecimalMin("0.0") Double price) {
    
}
