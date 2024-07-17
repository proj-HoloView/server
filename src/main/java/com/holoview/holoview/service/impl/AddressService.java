package com.holoview.holoview.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.holoview.holoview.controller.dto.address.InAddressDTO;
import com.holoview.holoview.model.entity.Address;
import com.holoview.holoview.model.entity.Shop;
import com.holoview.holoview.model.repository.AddressRepository;
import com.holoview.holoview.service.IAddressService;
import com.holoview.holoview.service.exception.NotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AddressService implements IAddressService {
    private final AddressRepository repository;
    private final ShopService shopService;

    @Override
    public Address create(InAddressDTO dto) {
        Shop shopFound = shopService.findById(dto.shopId());
        Address newAddress = new Address();

        BeanUtils.copyProperties(dto, newAddress);
        newAddress.setShop(shopFound);

        return repository.save(newAddress);
    }

    @Override
    public Address findById(UUID id) {
        Address addressFound = repository.findById(id).orElseThrow(NotFoundException::new);

        return addressFound;
    }

    @Override
    public List<Address> findAll() {
        List<Address> addressesFound = repository.findAll();

        return addressesFound;
    }

    @Override
    public Address update(UUID id, InAddressDTO dto) {
        Address addressFound = findById(id);

        BeanUtils.copyProperties(dto, addressFound);

        return repository.save(addressFound);
    }

    @Override
    public void delete(UUID id) {
        if(!repository.existsById(id)) throw new NotFoundException();

        repository.deleteById(id);
    }
}