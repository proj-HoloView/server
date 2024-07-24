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

    // POST
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

    // GET
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
    public List<Product> findAllByShopId(UUID shopId) {
        List<Product> productsFound = repository.findByShopId(shopId);

        return productsFound;
    }

    // PUT
    @Override
    public Product update(UUID id, InProductDTO dto) {
        Product productFound = findById(id);

        BeanUtils.copyProperties(dto, productFound);

        return repository.save(productFound);
    }

    // PATCH
    @Override
    public Product updatePrice(UUID id, Double newPrice) {
        Product productFound = this.findById(id);

        productFound.setPrice(newPrice);

        return repository.save(productFound);
    }

    @Override
    public Product updateAmount(UUID id, Integer newAmount) {
        Product productFound = this.findById(id);

        productFound.setAmount(newAmount);

        return repository.save(productFound);
    }

    // DELETE
    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new NotFoundException();

        repository.deleteById(id);
    }
}