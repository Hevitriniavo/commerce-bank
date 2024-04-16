package com.tantely.commerce.mappers;

import com.tantely.commerce.dto.CategoryPayload;
import com.tantely.commerce.entities.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class CategoryMapperTest {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    void testMapCategoryToCategoryPayload() {
        var category = Category.builder().id(1L).name("Fruits").build();
        var excepted = new CategoryPayload(1L, "Fruits");
        var result =  categoryMapper.toPayload(category);
        assertThat(result).isNotNull();
        assertThat(result).usingRecursiveComparison().isEqualTo(excepted);
        assertThat(category.getId()).isEqualTo(result.id());
        assertThat(category.getName()).isEqualTo(result.name());
    }

    @Test
    void testMapCategoryToCategoryPayloadReturnNull() {
        var result =  categoryMapper.toPayload(null);
        assertThat(result).isNull();
    }
}