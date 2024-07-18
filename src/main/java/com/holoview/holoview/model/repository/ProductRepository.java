package com.holoview.holoview.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.holoview.holoview.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    
}