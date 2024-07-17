package com.holoview.holoview.controller.dto.productCategory;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record InProductCategoryDTO(
        @NotNull @NotBlank @Size(min = 3, max = 50) String category,
        UUID shopId) {

}