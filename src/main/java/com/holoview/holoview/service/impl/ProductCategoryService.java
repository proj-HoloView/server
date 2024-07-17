package com.holoview.holoview.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.holoview.holoview.controller.dto.productCategory.InProductCategoryDTO;
import com.holoview.holoview.model.entity.ProductCategory;
import com.holoview.holoview.model.entity.Shop;
import com.holoview.holoview.model.repository.ProductCategoryRepository;
import com.holoview.holoview.service.IProductCategory;
import com.holoview.holoview.service.exception.NotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductCategoryService implements IProductCategory {
    private final ProductCategoryRepository repository;
    private final ShopService shopService;

    @Override
    public ProductCategory create(InProductCategoryDTO dto) {
        Shop shopFound = shopService.findById(dto.shopId());

        ProductCategory newCategory = new ProductCategory();

        BeanUtils.copyProperties(dto, newCategory);
        newCategory.setShop(shopFound);

        return repository.save(newCategory);
    }

    @Override
    public ProductCategory findById(UUID id) {
        ProductCategory categoryFound = repository.findById(id).orElseThrow(NotFoundException::new);

        return categoryFound;
    }

    @Override
    public List<ProductCategory> findAll() {
        List<ProductCategory> categoriesFound = repository.findAll();

        return categoriesFound;
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new NotFoundException();

        repository.deleteById(id);
    }
}