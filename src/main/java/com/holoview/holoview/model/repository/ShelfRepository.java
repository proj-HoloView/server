package com.holoview.holoview.model.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.holoview.holoview.model.entity.Shelf;

public interface ShelfRepository extends JpaRepository<Shelf, UUID> {
    Optional<Shelf> findByXAndYAndArrangementId(Integer x, Integer y, UUID arrangementId);
}