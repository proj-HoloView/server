package com.holoview.holoview.model.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class ShopArrangement {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private Integer sideSize;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Shop shop;

    @OneToMany(mappedBy = "arrangement")
    private List<InnactiveSquare> innactiveSquares;

    @OneToMany(mappedBy = "arrangement")
    private List<Shelf> shelf;
}