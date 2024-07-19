package com.holoview.holoview.controller.dto.shelf;

import java.util.UUID;

import com.holoview.holoview.model.entity.Shelf;

public record OutShelfDTO(UUID id, Integer x, Integer y) {
    public OutShelfDTO(Shelf s) {
        this(s.getId(), s.getX(), s.getY());
    }
}