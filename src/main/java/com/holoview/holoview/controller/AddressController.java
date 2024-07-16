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

import com.holoview.holoview.controller.dto.address.InAddressDTO;
import com.holoview.holoview.controller.dto.address.OutAddressDTO;
import com.holoview.holoview.model.entity.Address;
import com.holoview.holoview.service.impl.AddressService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("addresses")
public class AddressController {
    private final AddressService service;

    @PostMapping
    public ResponseEntity<OutAddressDTO> postAddress(@RequestBody InAddressDTO dto) throws URISyntaxException {
        Address address = service.create(dto);

        URI addressURI = new URI("/addresses/" + address.getId());

        return ResponseEntity.created(addressURI).body(new OutAddressDTO(address));
    }

    @GetMapping("{id}")
    public ResponseEntity<OutAddressDTO> getById(@PathVariable UUID id) {
        Address address = service.findById(id);

        return ResponseEntity.ok(new OutAddressDTO(address));
    }

    @GetMapping
    public ResponseEntity<List<OutAddressDTO>> getAll() {
        List<Address> addressesFound = service.findAll();

        if (addressesFound.isEmpty())
            return ResponseEntity.noContent().build();

        List<OutAddressDTO> dtos = addressesFound.stream()
                .map(OutAddressDTO::new)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<OutAddressDTO> updateAddress(@PathVariable UUID id, @RequestBody InAddressDTO dto) {
        Address addressUpdated = service.update(id, dto);

        return ResponseEntity.ok(new OutAddressDTO(addressUpdated));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable UUID id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}