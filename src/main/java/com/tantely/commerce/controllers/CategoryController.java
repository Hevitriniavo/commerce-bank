package com.tantely.commerce.controllers;

import com.tantely.commerce.dto.CategoryPayload;
import com.tantely.commerce.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/create/{name}")
    public CategoryPayload createCategory(@PathVariable String name){
        return categoryService.createCategory(name);
    }

    @GetMapping
    public List<CategoryPayload> getAllCategories(){
        return categoryService.getAllCategories();
    }

}
