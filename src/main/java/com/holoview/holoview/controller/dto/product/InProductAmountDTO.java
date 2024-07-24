package com.holoview.holoview.controller.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record InProductAmountDTO(@NotNull @Min(0) Integer amount) {
    
}
