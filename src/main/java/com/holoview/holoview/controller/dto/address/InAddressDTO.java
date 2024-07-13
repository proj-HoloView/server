package com.holoview.holoview.controller.dto.address;

import java.util.UUID;

public record InAddressDTO(String street, String number, String complement, String district, String city, UUID shopId) {
    
}