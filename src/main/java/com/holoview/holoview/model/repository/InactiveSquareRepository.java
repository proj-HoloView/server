package com.holoview.holoview.model.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.holoview.holoview.model.entity.InactiveSquare;

public interface InactiveSquareRepository extends JpaRepository<InactiveSquare, UUID> {
    Optional<InactiveSquare> findByXAndYAndArrangementId(Integer x, Integer y, UUID arrangementId);
}