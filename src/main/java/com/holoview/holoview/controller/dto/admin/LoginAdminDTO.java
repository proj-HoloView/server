package com.holoview.holoview.controller.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginAdminDTO(@NotNull @NotBlank String login, @NotNull @NotBlank String password) { }