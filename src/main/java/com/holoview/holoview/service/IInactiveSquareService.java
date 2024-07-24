package com.holoview.holoview.service;

import java.util.UUID;

import com.holoview.holoview.controller.dto.inactiveSquare.InInactiveSquareDTO;
import com.holoview.holoview.controller.dto.inactiveSquare.InInactiveSquareListDTO;
import com.holoview.holoview.model.entity.InactiveSquare;

public interface IInactiveSquareService extends
                IWritable<InactiveSquare, UUID, InInactiveSquareDTO> {
        void createList(InInactiveSquareListDTO dto);
}