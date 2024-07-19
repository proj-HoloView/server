package com.holoview.holoview.service;

import java.util.UUID;

import com.holoview.holoview.controller.dto.promotion.InPromotionDTO;
import com.holoview.holoview.model.entity.Promotion;

public interface IPromotionService extends
        IWritable<Promotion, UUID, InPromotionDTO>,
        IReadable<Promotion, UUID>,
        IEditable<Promotion, UUID, InPromotionDTO> {
}