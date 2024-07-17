package com.holoview.holoview.controller.dto.admin;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record InAdminDTO(
        @NotNull @NotBlank @Size(min = 5) String username,
        @NotNull @NotBlank @Email String email,
        @NotNull @NotBlank String password, UUID shopId) {
}