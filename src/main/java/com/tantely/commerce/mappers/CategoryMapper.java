package com.tantely.commerce.mappers;

import com.tantely.commerce.dto.CategoryPayload;
import com.tantely.commerce.entities.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryPayload toPayload(Category category){
        if (category == null){
            return null;
        }
        return new CategoryPayload(
                category.getId(),
                category.getName()
        );
    }
}
