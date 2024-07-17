package com.holoview.holoview.service;

import java.util.UUID;

import com.holoview.holoview.controller.dto.productCategory.InProductCategoryDTO;
import com.holoview.holoview.model.entity.ProductCategory;

public interface IProductCategory extends
        IWritable<ProductCategory, UUID, InProductCategoryDTO>,
        IReadable<ProductCategory, UUID> {

}