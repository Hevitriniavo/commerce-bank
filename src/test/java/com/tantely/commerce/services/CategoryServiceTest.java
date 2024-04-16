package com.tantely.commerce.services;

import com.tantely.commerce.dto.CategoryPayload;
import com.tantely.commerce.repositories.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class CategoryServiceTest {
    private CategoryPayload category;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;


    @BeforeEach
    void setUp() {
         category = new CategoryPayload(null, "Fruits");
    }

    @Test
    void createCategory() {
        category = categoryService.createCategory(category.name());
        assertThat(category.id()).isNotNull();
        assertThat(category.name()).isEqualTo("Fruits");
    }


    @AfterEach
    void tearDown() {
        if (category != null){
            categoryRepository.deleteById(category.id());
        }
    }


}