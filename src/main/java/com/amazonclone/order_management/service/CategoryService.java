package com.amazonclone.order_management.service;

import com.amazonclone.order_management.dto.CategoryResponse;
import com.amazonclone.order_management.dto.CreateCategoryRequest;
import com.amazonclone.order_management.entity.Category;
import com.amazonclone.order_management.exception.DuplicateResourceException;
import com.amazonclone.order_management.exception.ResourceNotFoundException;
import com.amazonclone.order_management.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponse createCategory(
            CreateCategoryRequest request) {

        if (categoryRepository
                .existsByNameIgnoreCase(request.getName())) {

            throw new DuplicateResourceException(
                    "Category already exists"
            );
        }

        Category category =
                new Category(request.getName());

        Category savedCategory =
                categoryRepository.save(category);

        return new CategoryResponse(
                savedCategory.getId(),
                savedCategory.getName()
        );
    }

    public Category getCategory(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found"
                        )
                );
    }

    public List<CategoryResponse> getAllCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(category ->
                        new CategoryResponse(
                                category.getId(),
                                category.getName()
                        )
                )
                .toList();
    }
}