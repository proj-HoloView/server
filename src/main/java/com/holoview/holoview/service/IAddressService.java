package com.holoview.holoview.service;

import java.util.UUID;

import com.holoview.holoview.controller.dto.address.InAddressDTO;
import com.holoview.holoview.model.entity.Address;

public interface IAddressService extends
                IWritable<Address, UUID, InAddressDTO>,
                IEditable<Address, UUID, InAddressDTO>,
                IReadable<Address, UUID> {

}