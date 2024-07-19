package com.holoview.holoview.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.holoview.holoview.controller.dto.promotion.InPromotionDTO;
import com.holoview.holoview.controller.dto.promotion.OutPromotionDTO;
import com.holoview.holoview.model.entity.Promotion;
import com.holoview.holoview.service.impl.PromotionService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("promotions")
public class PromotionController {
    private final PromotionService service;

    @PostMapping
    public ResponseEntity<OutPromotionDTO> createPromotion(@RequestBody @Valid InPromotionDTO dto) throws URISyntaxException {
        Promotion newPromotion = service.create(dto);

        URI promotionUri = new URI("/promotions/" + newPromotion.getId());

        return ResponseEntity.created(promotionUri).body(new OutPromotionDTO(newPromotion));
    }

    @GetMapping("{id}")
    public ResponseEntity<OutPromotionDTO> findById(@PathVariable UUID id) {
        Promotion promotionFound = service.findById(id);

        return ResponseEntity.ok(new OutPromotionDTO(promotionFound));
    }

    @GetMapping
    public ResponseEntity<List<OutPromotionDTO>> findAll() {
        List<Promotion> promotionsFound = service.findAll();

        List<OutPromotionDTO> dtos = promotionsFound.stream()
                .map(OutPromotionDTO::new)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<OutPromotionDTO> updatePromotion(@PathVariable UUID id, @RequestBody @Valid InPromotionDTO dto) {
        Promotion promotionFound = service.update(id, dto);

        return ResponseEntity.ok(new OutPromotionDTO(promotionFound));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePromotion(@PathVariable UUID id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}