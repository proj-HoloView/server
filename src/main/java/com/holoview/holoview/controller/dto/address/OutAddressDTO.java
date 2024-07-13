package com.holoview.holoview.controller.dto.address;

import java.util.UUID;

import com.holoview.holoview.model.entity.Address;

public record OutAddressDTO(UUID id, String street, String number, String complement, String district, String city) {
    public OutAddressDTO(Address a) {
        this(a.getId(), a.getStreet(), a.getNumber(), a.getComplement(), a.getDistrict(), a.getCity());
    }
}