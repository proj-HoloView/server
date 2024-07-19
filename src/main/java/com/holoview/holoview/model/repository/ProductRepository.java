package com.holoview.holoview.model.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.holoview.holoview.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByShopId(UUID shopId);
}