package com.amazonclone.order_management.service;

import com.amazonclone.order_management.dto.CreateProductRequest;
import com.amazonclone.order_management.dto.ProductResponse;
import com.amazonclone.order_management.entity.Category;
import com.amazonclone.order_management.entity.Product;
import com.amazonclone.order_management.exception.DuplicateResourceException;
import com.amazonclone.order_management.exception.ResourceNotFoundException;
import com.amazonclone.order_management.repository.CategoryRepository;
import com.amazonclone.order_management.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(
            ProductRepository productRepository,
            CategoryRepository categoryRepository) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductResponse createProduct(
            CreateProductRequest request) {

        if (productRepository.existsBySku(request.getSku())) {
            throw new DuplicateResourceException(
                    "Product with this SKU already exists"
            );
        }

        Category category =
                categoryRepository.findById(
                        request.getCategoryId()
                ).orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found"
                        )
                );

        Product product = new Product(
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getSku(),
                category
        );

        Product savedProduct =
                productRepository.save(product);

        return toResponse(savedProduct);
    }

    public List<ProductResponse> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProductResponse getProduct(Long id) {

        Product product =
                productRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Product not found"
                                )
                        );

        return toResponse(product);
    }

    private ProductResponse toResponse(Product product) {

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getSku(),
                product.getCategory().getId(),
                product.getCategory().getName()
        );
    }
}
