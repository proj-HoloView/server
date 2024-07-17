package com.holoview.holoview.controller.dto.shop;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record InShopDTO(
        @NotBlank @NotNull @Size(min = 6, message = "Nome da loje deve conter mais de 6 digitos") String name) {

}