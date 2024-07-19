package com.holoview.holoview.controller.dto.inactiveSquare;

import java.util.UUID;

import com.holoview.holoview.model.entity.InactiveSquare;

public record OutInactiveSquareDTO(UUID id, Integer x, Integer y) {
    public OutInactiveSquareDTO(InactiveSquare is) {
        this(is.getId(), is.getX(), is.getY());
    }
}