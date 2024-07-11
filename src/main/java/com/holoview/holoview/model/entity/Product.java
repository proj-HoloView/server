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

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Double price;

    @Column
    private Integer amount;

    @Column
    private String picture;

    @ManyToOne
    private ProductCategory category;

    @ManyToOne
    private Shop shop;

    @OneToMany(mappedBy = "product")
    private List<ShelfProduct> shelfProducts;

    @OneToMany(mappedBy = "product")
    private List<Promotion> promotions;
}