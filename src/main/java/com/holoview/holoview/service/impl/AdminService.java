package com.holoview.holoview.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.holoview.holoview.controller.dto.admin.InAdminDTO;
import com.holoview.holoview.model.entity.Admin;
import com.holoview.holoview.model.entity.Shop;
import com.holoview.holoview.model.repository.AdminRepository;
import com.holoview.holoview.service.IAdminService;
import com.holoview.holoview.service.exception.ConflictException;
import com.holoview.holoview.service.exception.NotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminService implements IAdminService {
    private final AdminRepository repository;
    private final ShopService shopService;
    private final PasswordEncoder passwordEncoder;

    // POST
    @Override
    public Admin create(InAdminDTO dto) {
        if (repository.existsByEmail(dto.email())) {
            throw new ConflictException();
        }

        Shop shopFound = shopService.findById(dto.shopId());

        Admin newAdmin = new Admin();

        BeanUtils.copyProperties(dto, newAdmin);
        newAdmin.setShop(shopFound);
        newAdmin.setPassword(passwordEncoder.encode(dto.password()));

        return repository.save(newAdmin);
    }

    // GET
    @Override
    public Admin findById(UUID id) {
        Admin adminFound = repository.findById(id).orElseThrow(NotFoundException::new);

        return adminFound;
    }

    @Override
    public Admin findByEmail(String email) {
        Admin adminFound = repository.findByEmail(email).orElseThrow(NotFoundException::new);

        return adminFound;
    }

    @Override
    public Admin findByEmailOrUsername(String login) {
        Admin adminFound = repository.findByEmailOrUsername(login).orElseThrow(NotFoundException::new);

        return adminFound;
    }

    @Override
    public List<Admin> findAll() {
        List<Admin> adminsFound = repository.findAll();

        return adminsFound;
    }

    // PUT
    @Override
    public Admin update(UUID id, InAdminDTO dto) {
        Admin adminFound = findById(id);

        BeanUtils.copyProperties(dto, adminFound);

        return repository.save(adminFound);
    }

    // DELETE
    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new NotFoundException();

        repository.deleteById(id);
    }
}