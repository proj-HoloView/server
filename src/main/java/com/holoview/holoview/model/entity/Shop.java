package com.holoview.holoview.model.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String name;

    @Column
    private Boolean active;

    @OneToOne(mappedBy = "shop")
    private Address address;

    @OneToOne(mappedBy = "shop")
    private ShopArrangement arrangement;

    @OneToMany(mappedBy = "shop")
    private List<Product> products;

    @OneToMany(mappedBy = "shop")
    private List<Admin> admins;

    @OneToMany(mappedBy = "shop")
    private List<ProductCategory> categories;
}