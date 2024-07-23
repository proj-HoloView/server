package com.holoview.holoview.controller.dto.admin;

import java.util.UUID;

import com.holoview.holoview.model.entity.Admin;

public record OutAdminTokenDTO(UUID id, String username, String email, String token) {
    public OutAdminTokenDTO(Admin a, String token) {
        this(a.getId(), a.getUsername(), a.getEmail(), token);
    }
}