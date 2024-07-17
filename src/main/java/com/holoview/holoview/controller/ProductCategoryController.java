package com.holoview.holoview.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.holoview.holoview.controller.dto.productCategory.InProductCategoryDTO;
import com.holoview.holoview.controller.dto.productCategory.OutProductCategoryDTO;
import com.holoview.holoview.model.entity.ProductCategory;
import com.holoview.holoview.service.impl.ProductCategoryService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("product-categories")
public class ProductCategoryController {
    private final ProductCategoryService service;

    @PostMapping
    public ResponseEntity<OutProductCategoryDTO> createCategory(@RequestBody @Valid InProductCategoryDTO dto)
            throws URISyntaxException {
        ProductCategory newCategory = service.create(dto);

        URI categoryUri = new URI("/product-categories/" + newCategory.getId());

        return ResponseEntity.created(categoryUri).body(new OutProductCategoryDTO(newCategory));
    }

    @GetMapping("{id}")
    public ResponseEntity<OutProductCategoryDTO> findById(@PathVariable UUID id) {
        ProductCategory categoryFound = service.findById(id);

        return ResponseEntity.ok(new OutProductCategoryDTO(categoryFound));
    }

    @GetMapping
    public ResponseEntity<List<OutProductCategoryDTO>> findAll() {
        List<ProductCategory> categoriesFound = service.findAll();

        if (categoriesFound.isEmpty())
            return ResponseEntity.noContent().build();

        List<OutProductCategoryDTO> dtos = categoriesFound.stream()
                .map(OutProductCategoryDTO::new)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable UUID id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}