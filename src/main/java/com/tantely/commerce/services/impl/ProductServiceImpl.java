package com.tantely.commerce.services.impl;

import com.tantely.commerce.dto.CategoryPayload;
import com.tantely.commerce.dto.Paginate;
import com.tantely.commerce.dto.ProductWithCategory;
import com.tantely.commerce.dto.requests.ProductRequest;
import com.tantely.commerce.dto.ProductPayload;
import com.tantely.commerce.exceptions.EntityInternalServerException;
import com.tantely.commerce.exceptions.EntityNotFoundException;
import com.tantely.commerce.mappers.ProductMapper;
import com.tantely.commerce.repositories.CategoryRepository;
import com.tantely.commerce.repositories.ProductRepository;
import com.tantely.commerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final CategoryRepository categoryRepository;

    @Override
    public ProductPayload createProductWithCategoryId(ProductRequest toCreate, Long categoryId) {
        try {
            var foundCategoryOptional = categoryRepository.findById(categoryId);
            if (foundCategoryOptional.isPresent()) {
                var foundCategory = foundCategoryOptional.get();
                var product = productMapper.toProduct(toCreate);
                 product.setCategory(foundCategory);
                var toCreated = productRepository.save(product);
                return productMapper.toPayload(toCreated);
            } else {
                throw new EntityNotFoundException("A category with ID: '" + categoryId+" 'does not exist.");
            }
        } catch (Exception e) {
            throw new EntityInternalServerException("Failed to create category: " + e.getMessage(), e);
        }
    }

    public Paginate<ProductPayload> getAllProducts(int pageNumber, int pageSize) {
        var sort = Sort.by(Sort.Direction.ASC, "id");
        var pageable = PageRequest.of(pageNumber, pageSize, sort);
        var productsPage = productRepository.findAll(pageable);
        var productPayloads = productsPage.getContent()
                .stream()
                .map(productMapper::toPayload)
                .collect(Collectors.toList());
        return Paginate.<ProductPayload>builder()
                .content(productPayloads)
                .totalElements(productsPage.getTotalElements())
                .totalPages(productsPage.getTotalPages())
                .numberOfElements(productsPage.getNumberOfElements())
                .hasNextPage(productsPage.hasNext())
                .hasPreventPage( pageNumber > 0)
                .build();
    }

    @Override
    public ProductWithCategory getCategoryWithProductByIdProduct(Long productId) {
        var product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("A product with id: '" + productId + "' not found. "));
       return new ProductWithCategory(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity(), product.getImageUrl(), product.getIsActive(), new CategoryPayload(
               product.getCategory().getId(),
               product.getCategory().getName()
       ));
    }
}
