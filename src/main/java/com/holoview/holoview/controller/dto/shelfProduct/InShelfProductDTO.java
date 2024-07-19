package com.holoview.holoview.controller.dto.shelfProduct;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record InShelfProductDTO(@NotNull @Min(0) Integer amount, UUID productId, UUID shelfId) {

}