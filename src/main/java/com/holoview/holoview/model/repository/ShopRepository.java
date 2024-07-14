package com.holoview.holoview.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.holoview.holoview.model.entity.Shop;

public interface ShopRepository extends JpaRepository<Shop, UUID> {

}
