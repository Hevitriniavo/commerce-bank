package com.tantely.commerce.mappers;

import com.tantely.commerce.dto.ProductPayload;
import com.tantely.commerce.dto.requests.ProductRequest;
import com.tantely.commerce.entities.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ProductMapperTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    void testMapProductPayloadToProduct() {
        var productRequest = new ProductRequest("pomme", "Pomme fraîche et juteuse", 1.5, 23L, "http://example.com/apple.jpg", true);
        var result = productMapper.toProduct(productRequest);
        var expected = Product.builder().name("pomme").description("Pomme fraîche et juteuse").price(1.5).quantity(23L).imageUrl("http://example.com/apple.jpg").isActive(true).build();

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(expected.getName());
        assertThat(result.getDescription()).isEqualTo(expected.getDescription());
        assertThat(result.getPrice()).isEqualTo(expected.getPrice());
        assertThat(result.getQuantity()).isEqualTo(expected.getQuantity());
        assertThat(result.getImageUrl()).isEqualTo(expected.getImageUrl());
        assertThat(result.getIsActive()).isEqualTo(expected.getIsActive());
    }

    @Test
    void testMapProductToProductPayload() {
        var product = Product.builder().id(1L).name("pomme").description("Pomme fraîche et juteuse").price(1.5).quantity(23L).imageUrl("http://example.com/apple.jpg").isActive(true).build();
        var result = productMapper.toPayload(product);
        var expected = new ProductPayload(1L,"pomme", "Pomme fraîche et juteuse", 1.5, 23L, "http://example.com/apple.jpg", true);

        assertThat(result).isNotNull();
        assertThat(result.name()).isEqualTo(expected.name());
        assertThat(result.description()).isEqualTo(expected.description());
        assertThat(result.price()).isEqualTo(expected.price());
        assertThat(result.quantity()).isEqualTo(expected.quantity());
        assertThat(result.imageUrl()).isEqualTo(expected.imageUrl());
        assertThat(result.isActive()).isEqualTo(expected.isActive());
    }


    @Test
    void testMapProductPayloadToProductReturnNull() {
        var result = productMapper.toProduct(null);
        assertThat(result).isNull();
    }

    @Test
    void testMapProductToProductPayloadReturnNull() {
        var result = productMapper.toPayload(null);
        assertThat(result).isNull();
    }
}