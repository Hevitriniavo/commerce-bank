package com.tantely.commerce.services.impl;

import com.tantely.commerce.dto.CategoryPayload;
import com.tantely.commerce.entities.Category;
import com.tantely.commerce.exceptions.EntityBadRequestException;
import com.tantely.commerce.exceptions.EntityInternalServerException;
import com.tantely.commerce.mappers.CategoryMapper;
import com.tantely.commerce.repositories.CategoryRepository;
import com.tantely.commerce.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Override
    public CategoryPayload createCategory(String name) {
         try {
            if (categoryRepository.existsByName(name)){
                throw new EntityBadRequestException("A category with the name '" + name + "' already exists");
            }
            var category = categoryRepository.save(Category.builder().name(name).build());
            return categoryMapper.toPayload(category);
         } catch (Exception e){
           throw new EntityInternalServerException("Failed to create category: " + e.getMessage(), e);
         }
    }

    @Override
    public List<CategoryPayload> getAllCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(categoryMapper::toPayload)
                .toList();
    }

}
