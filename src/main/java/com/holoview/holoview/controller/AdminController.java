package com.holoview.holoview.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.holoview.holoview.controller.dto.admin.InAdminDTO;
import com.holoview.holoview.controller.dto.admin.OutAdminDTO;
import com.holoview.holoview.controller.dto.admin.OutAdminTokenDTO;
import com.holoview.holoview.infra.security.TokenService;
import com.holoview.holoview.model.entity.Admin;
import com.holoview.holoview.service.impl.AdminService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("admins")
public class AdminController {
    private final AdminService service;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<OutAdminTokenDTO> createAdmin(@RequestBody @Valid InAdminDTO dto) throws URISyntaxException {
        Admin newAdmin = service.create(dto);

        URI adminURI = new URI("/admins/" + newAdmin.getId());

        String token = this.tokenService.generateToken(newAdmin);

        return ResponseEntity.created(adminURI).body(new OutAdminTokenDTO(newAdmin, token));
    }

    @PostMapping("/login")
    public ResponseEntity<OutAdminTokenDTO> login(@RequestBody InAdminDTO dto) {
        Admin adminFound = service.findByEmail(dto.email());

        if (passwordEncoder.matches(dto.password(), adminFound.getPassword())) {
            String token = this.tokenService.generateToken(adminFound);

            return ResponseEntity.ok(new OutAdminTokenDTO(adminFound, token));
        }

        return ResponseEntity.badRequest().build();
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