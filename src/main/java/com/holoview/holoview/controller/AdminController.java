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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.holoview.holoview.controller.dto.admin.InAdminDTO;
import com.holoview.holoview.controller.dto.admin.OutAdminDTO;
import com.holoview.holoview.model.entity.Admin;
import com.holoview.holoview.service.impl.AdminService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@AllArgsConstructor
@RequestMapping("admins")
public class AdminController {
    private final AdminService service;

    @PostMapping
    public ResponseEntity<OutAdminDTO> createAdmin(@RequestBody @Valid InAdminDTO dto) throws URISyntaxException {
        Admin newAdmin = service.create(dto);

        URI adminURI = new URI("/admins/" + newAdmin.getId());

        return ResponseEntity.created(adminURI).body(new OutAdminDTO(newAdmin));
    }

    @GetMapping("{id}")
    public ResponseEntity<OutAdminDTO> findById(@PathVariable UUID id) {
        Admin adminFound = service.findById(id);

        return ResponseEntity.ok(new OutAdminDTO(adminFound));
    }

    @GetMapping
    public ResponseEntity<List<OutAdminDTO>> findAll() {
        List<Admin> adminsFound = service.findAll();

        if (adminsFound.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<OutAdminDTO> dtos = adminsFound.stream()
                .map(OutAdminDTO::new)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<OutAdminDTO> updateAdmin(@PathVariable UUID id, @RequestBody @Valid InAdminDTO dto) {
        Admin adminUpdated = service.update(id, dto);

        return ResponseEntity.ok(new OutAdminDTO(adminUpdated));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable UUID id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}