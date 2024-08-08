package com.holoview.holoview.service;

import org.springframework.web.multipart.MultipartFile;

import com.holoview.holoview.model.entity.Product;
import com.holoview.holoview.model.entity.Shop;

public interface IBlobStorageService {
    String uploadBlob(Shop shop, Product product, MultipartFile file);
}