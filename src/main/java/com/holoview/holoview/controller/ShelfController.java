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

import com.holoview.holoview.controller.dto.shelf.InShelfDTO;
import com.holoview.holoview.controller.dto.shelf.OutShelfDTO;
import com.holoview.holoview.model.entity.Shelf;
import com.holoview.holoview.service.impl.ShelfService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("shelfs")
public class ShelfController {
    private final ShelfService service;

    @PostMapping
    public ResponseEntity<OutShelfDTO> createShelf(@RequestBody @Valid InShelfDTO dto) throws URISyntaxException {
        Shelf newShelf = service.create(dto);

        URI shelfUri = new URI("/shelfs/" + newShelf.getId());

        return ResponseEntity.created(shelfUri).body(new OutShelfDTO(newShelf));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<OutShelfDTO> deleteShelf(@PathVariable UUID id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}