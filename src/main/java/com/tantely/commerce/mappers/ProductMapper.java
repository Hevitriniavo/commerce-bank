package com.tantely.commerce.mappers;

import com.tantely.commerce.dto.ProductPayload;
import com.tantely.commerce.dto.requests.ProductRequest;
import com.tantely.commerce.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toProduct(ProductRequest request){
        if (request == null){
            return null;
        }
        return Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .quantity(request.quantity())
                .imageUrl(request.imageUrl())
                .isActive(request.isActive())
                .build();
    }

    public ProductPayload toPayload(Product product) {
        if (product == null){
            return null;
        }
        return new ProductPayload(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getImageUrl(),
                product.getIsActive()
        );
    }
}
