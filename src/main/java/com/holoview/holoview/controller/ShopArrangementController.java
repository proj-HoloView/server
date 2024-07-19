package com.holoview.holoview.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.holoview.holoview.controller.dto.shopArragement.InShopArrangementDTO;
import com.holoview.holoview.controller.dto.shopArragement.OutShopArrangementDTO;
import com.holoview.holoview.model.entity.ShopArrangement;
import com.holoview.holoview.service.impl.ShopArrangementService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("shop-arrangements")
public class ShopArrangementController {
    private final ShopArrangementService service;

    @PostMapping
    public ResponseEntity<OutShopArrangementDTO> createShopArragement(@RequestBody @Valid InShopArrangementDTO dto)
            throws URISyntaxException {
        ShopArrangement newShopArrangement = service.create(dto);

        URI shopArrangementUri = new URI("/shop-arrangements/" + newShopArrangement.getId());

        return ResponseEntity.created(shopArrangementUri).body(new OutShopArrangementDTO(newShopArrangement));
    }

    @PutMapping("{id}")
    public ResponseEntity<OutShopArrangementDTO> updateShopArrangement(@PathVariable UUID id,
            @RequestBody @Valid InShopArrangementDTO dto) {
        ShopArrangement updatedShopArrangement = service.update(id, dto);

        return ResponseEntity.ok(new OutShopArrangementDTO(updatedShopArrangement));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteShopArragement(@PathVariable UUID id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}