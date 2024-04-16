package com.tantely.commerce.services;

import com.tantely.commerce.dto.CategoryPayload;
import java.util.List;


public interface CategoryService {
    CategoryPayload createCategory(String name);

    List<CategoryPayload> getAllCategories();
}
