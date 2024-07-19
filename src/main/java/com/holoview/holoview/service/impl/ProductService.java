package com.holoview.holoview.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.holoview.holoview.controller.dto.product.InProductDTO;
import com.holoview.holoview.model.entity.Product;
import com.holoview.holoview.model.entity.ProductCategory;
import com.holoview.holoview.model.entity.Shop;
import com.holoview.holoview.model.repository.ProductRepository;
import com.holoview.holoview.service.IProductService;
import com.holoview.holoview.service.exception.NotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository repository;
    private final ShopService shopService;
    private final ProductCategoryService categoryService;

    @Override
    public Product create(InProductDTO dto) {
        Shop shopFound = shopService.findById(dto.shopId());
        ProductCategory categoryFound = categoryService.findById(dto.categoryId());

        Product newProduct = new Product();

        BeanUtils.copyProperties(dto, newProduct);
        newProduct.setShop(shopFound);
        newProduct.setCategory(categoryFound);

        return repository.save(newProduct);
    }

    @Override
    public Product findById(UUID id) {
        Product productFound = repository.findById(id).orElseThrow(NotFoundException::new);

        return productFound;
    }

    @Override
    public List<Product> findAll() {
        List<Product> productsFound = repository.findAll();

        return productsFound;
    }

    @Override
    public Product update(UUID id, InProductDTO dto) {
        Product productFound = findById(id);

        BeanUtils.copyProperties(dto, productFound);

        return repository.save(productFound);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new NotFoundException();

        repository.deleteById(id);
    }
}