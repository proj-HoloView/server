package com.holoview.holoview.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.holoview.holoview.controller.dto.product.InProductAmountDTO;
import com.holoview.holoview.controller.dto.product.InProductDTO;
import com.holoview.holoview.controller.dto.product.InProductPriceDTO;
import com.holoview.holoview.controller.dto.product.OutProductDTO;
import com.holoview.holoview.model.entity.Product;
import com.holoview.holoview.service.impl.ProductService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("products")
public class ProductController {
    private final ProductService service;

    // POST
    @PostMapping
    public ResponseEntity<OutProductDTO> createProduct(@RequestBody @Valid InProductDTO dto) throws URISyntaxException {
        Product newProduct = service.create(dto);

        URI productUri = new URI("/product/" + newProduct.getId());

        return ResponseEntity.created(productUri).body(new OutProductDTO(newProduct));
    }

    // GET
    @GetMapping("{id}")
    public ResponseEntity<OutProductDTO> findById(@PathVariable UUID id) {
        Product productFound = service.findById(id);

        return ResponseEntity.ok(new OutProductDTO(productFound));
    }

    @GetMapping
    public ResponseEntity<List<OutProductDTO>> findAll() {
        List<Product> productsFound = service.findAll();

        if (productsFound.isEmpty())
            return ResponseEntity.noContent().build();

        List<OutProductDTO> dtos = productsFound.stream()
                .map(OutProductDTO::new)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("by-shop/{shopId}")
    public ResponseEntity<List<OutProductDTO>> findAllByShopId(@PathVariable UUID shopId) {
        List<Product> productsFound = service.findAllByShopId(shopId);

        if (productsFound.isEmpty())
            return ResponseEntity.noContent().build();

        List<OutProductDTO> dtos = productsFound.stream()
                .map(OutProductDTO::new)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    // PUT
    @PutMapping("{id}")
    public ResponseEntity<OutProductDTO> updateProduct(@PathVariable UUID id, @RequestBody @Valid InProductDTO dto) {
        Product updateProduct = service.update(id, dto);

        return ResponseEntity.ok(new OutProductDTO(updateProduct));
    }

    // PATCH
    @PatchMapping("price/{id}")
    public ResponseEntity<OutProductDTO> updatePrice(@PathVariable UUID id, @RequestBody @Valid InProductPriceDTO dto) {
        Product updateProduct = service.updatePrice(id, dto.price());

        return ResponseEntity.ok(new OutProductDTO(updateProduct));
    }

    @PatchMapping("amount/{id}")
    public ResponseEntity<OutProductDTO> updateAmount(@PathVariable UUID id,
            @RequestBody @Valid InProductAmountDTO dto) {
        Product updateProduct = service.updateAmount(id, dto.amount());

        return ResponseEntity.ok(new OutProductDTO(updateProduct));
    }

    @PatchMapping(path = "picture/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<OutProductDTO> uploadPicture(@PathVariable UUID id, @RequestParam MultipartFile picture) {
        Product updateProduct = service.updatePicture(id, picture);

        return ResponseEntity.ok(new OutProductDTO(updateProduct));
    }

    // DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable UUID id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}