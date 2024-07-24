package com.holoview.holoview.model.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Shelf {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private Integer x;

    @Column
    private Integer y;

    @ToString.Exclude
    @ManyToOne
    private ShopArrangement arrangement;

    @OneToMany(mappedBy = "shelf")
    private List<ShelfProduct> shelfProducts;
}