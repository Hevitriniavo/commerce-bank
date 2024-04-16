package com.tantely.commerce.controllers;

import com.tantely.commerce.dto.Paginate;
import com.tantely.commerce.dto.ProductPayload;
import com.tantely.commerce.dto.ProductWithCategory;
import com.tantely.commerce.dto.requests.ProductRequest;
import com.tantely.commerce.entities.Product;
import com.tantely.commerce.files.FileService;
import com.tantely.commerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final FileService fileService;

    @GetMapping
    public Paginate<ProductPayload> getAllProducts(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        return productService.getAllProducts(pageNumber, pageSize);
    }

    @GetMapping("/{productId}")
    public ProductWithCategory getCategoryWithProductByIdProduct(@PathVariable Long productId){
        return productService.getCategoryWithProductByIdProduct(productId);
    }

    @Transactional
    @PostMapping("/{categoryId}")
    public ProductPayload createProductWithCategoryId(
            @PathVariable Long categoryId,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Double price,
            @RequestParam Long quantity,
            @RequestParam Boolean isActive,
            @RequestParam("file") MultipartFile file
    ) {
        var imageUrl = fileService.saveFile(file);
        var productRequest = new ProductRequest(name, description, price, quantity, imageUrl, isActive);
        return productService.createProductWithCategoryId(productRequest, categoryId);
    }
}
