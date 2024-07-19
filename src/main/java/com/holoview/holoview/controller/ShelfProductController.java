package com.holoview.holoview.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.holoview.holoview.controller.dto.shelfProduct.InShelfProductDTO;
import com.holoview.holoview.controller.dto.shelfProduct.OutShelfProductDTO;
import com.holoview.holoview.model.entity.ShelfProduct;
import com.holoview.holoview.service.impl.ShelfProductService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("shelf-products")
public class ShelfProductController {
    private final ShelfProductService service;

    @PostMapping
    public ResponseEntity<OutShelfProductDTO> createShelfProduct(@RequestBody @Valid InShelfProductDTO dto) throws URISyntaxException {
        ShelfProduct newShelfProduct = service.create(dto);

        URI shelfProductUri = new URI("/shelf-products/" + newShelfProduct.getId());

        return ResponseEntity.created(shelfProductUri).body(new OutShelfProductDTO(newShelfProduct));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteShelfProduct(@PathVariable UUID id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}