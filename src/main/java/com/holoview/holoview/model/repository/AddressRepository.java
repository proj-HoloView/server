package com.holoview.holoview.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.holoview.holoview.model.entity.Address;

public interface AddressRepository extends JpaRepository<Address, UUID> {

}