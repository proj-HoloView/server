package com.holoview.holoview.controller.dto.address;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record InAddressDTO(
                @NotNull @NotBlank @Size(min = 5) String street,
                @NotNull @NotBlank String number,
                String complement,
                String district,
                @NotNull @NotBlank String city,
                @NotNull @NotBlank String zipCode,
                UUID shopId) {

}