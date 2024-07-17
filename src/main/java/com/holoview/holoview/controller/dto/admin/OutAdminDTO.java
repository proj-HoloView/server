package com.holoview.holoview.controller.dto.admin;

import java.util.UUID;

import com.holoview.holoview.model.entity.Admin;

public record OutAdminDTO(UUID id, String username, String email) {
    public OutAdminDTO(Admin a) {
        this(a.getId(), a.getUsername(), a.getEmail());
    }
}