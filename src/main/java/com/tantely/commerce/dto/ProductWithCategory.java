package com.tantely.commerce.dto;

import java.util.List;

public record ProductWithCategory(
        Long id,

        String name,

        String description,

        Double price,

        Long quantity,

        String imageUrl,

        Boolean isActive,

        CategoryPayload category
) {
}
