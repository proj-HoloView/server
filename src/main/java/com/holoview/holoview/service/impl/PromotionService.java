package com.holoview.holoview.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.holoview.holoview.controller.dto.promotion.InPromotionDTO;
import com.holoview.holoview.model.entity.Product;
import com.holoview.holoview.model.entity.Promotion;
import com.holoview.holoview.model.repository.PromotionRepository;
import com.holoview.holoview.service.IPromotionService;
import com.holoview.holoview.service.exception.NotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PromotionService implements IPromotionService {
    private final PromotionRepository repository;
    private final ProductService productService;

    @Override
    public Promotion create(InPromotionDTO dto) {
        Product productFound = productService.findById(dto.productId());

        Promotion newPromotion = new Promotion();
        BeanUtils.copyProperties(dto, newPromotion);
        newPromotion.setProduct(productFound);

        return repository.save(newPromotion);
    }

    @Override
    public Promotion findById(UUID id) {
        Promotion promotionFound = repository.findById(id).orElseThrow(NotFoundException::new);

        return promotionFound;
    }

    @Override
    public List<Promotion> findAll() {
        List<Promotion> promotionsFound = repository.findAll();

        return promotionsFound;
    }

    @Override
    public Promotion update(UUID id, InPromotionDTO dto) {
        Promotion promotionFound = findById(id);

        BeanUtils.copyProperties(dto, promotionFound);

        return repository.save(promotionFound);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new NotFoundException();

        repository.deleteById(id);
    }
}