package com.holoview.holoview.service;

import java.util.UUID;

import com.holoview.holoview.controller.dto.shelf.InShelfDTO;
import com.holoview.holoview.model.entity.Shelf;

public interface IShelfService extends
        IWritable<Shelf, UUID, InShelfDTO> {

}