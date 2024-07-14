package com.holoview.holoview.controller;

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

import com.holoview.holoview.controller.dto.shop.InShopDTO;
import com.holoview.holoview.controller.dto.shop.OutShopDTO;
import com.holoview.holoview.model.entity.Shop;
import com.holoview.holoview.service.impl.ShopService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("shops")
public class ShopController {
    private final ShopService service;

    @PostMapping
    public ResponseEntity<OutShopDTO> postShop(@RequestBody InShopDTO dto) {
        Shop newShop = service.create(dto);

        return ResponseEntity.ok(new OutShopDTO(newShop));
    }

    @GetMapping("{id}")
    public ResponseEntity<OutShopDTO> getById(@PathVariable UUID id) {
        Shop shopFound = service.findById(id);

        return ResponseEntity.ok(new OutShopDTO(shopFound));
    }

    @GetMapping
    public ResponseEntity<List<OutShopDTO>> getAll() {
        List<Shop> shopsFound = service.findAll();

        if (shopsFound.isEmpty())
            return ResponseEntity.noContent().build();

        List<OutShopDTO> dtos = shopsFound.stream()
                .map(OutShopDTO::new)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<OutShopDTO> updateShop(@PathVariable UUID id, @RequestBody InShopDTO dto) {
        Shop shopUpdated = service.update(id, dto);

        return ResponseEntity.ok(new OutShopDTO(shopUpdated));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteShop(@PathVariable UUID id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
