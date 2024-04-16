package com.tantely.commerce.services;

import com.tantely.commerce.dto.Paginate;
import com.tantely.commerce.dto.ProductWithCategory;
import com.tantely.commerce.dto.requests.ProductRequest;
import com.tantely.commerce.dto.ProductPayload;


public interface ProductService {
    ProductPayload createProductWithCategoryId(ProductRequest toCreate, Long categoryId);

    Paginate<ProductPayload> getAllProducts(int pageNumber, int pageSize);

    ProductWithCategory getCategoryWithProductByIdProduct(Long productId);
}
