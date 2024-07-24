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

import com.holoview.holoview.controller.dto.inactiveSquare.InInactiveSquareDTO;
import com.holoview.holoview.controller.dto.inactiveSquare.InInactiveSquareListDTO;
import com.holoview.holoview.controller.dto.inactiveSquare.OutInactiveSquareDTO;
import com.holoview.holoview.model.entity.InactiveSquare;
import com.holoview.holoview.service.impl.InactiveSquareService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("inactive-squares")
public class InactiveSquareController {
    private final InactiveSquareService service;

    // POST
    @PostMapping
    public ResponseEntity<OutInactiveSquareDTO> createInactiveSquare(@RequestBody @Valid InInactiveSquareDTO dto)
            throws URISyntaxException {
        InactiveSquare newInactiveSquare = service.create(dto);

        URI inactiveSquareuri = new URI("inactive-squares" + newInactiveSquare.getId());

        return ResponseEntity.created(inactiveSquareuri).body(new OutInactiveSquareDTO(newInactiveSquare));
    }

    @PostMapping("list")
    public ResponseEntity<?> createInactiveSquareList(@RequestBody @Valid InInactiveSquareListDTO dto) {
        service.createList(dto);

        return ResponseEntity.ok().build();
    }

    // DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteInactiveSquare(@PathVariable UUID id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}