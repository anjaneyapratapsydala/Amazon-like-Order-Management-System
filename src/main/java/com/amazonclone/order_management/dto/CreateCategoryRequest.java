package com.amazonclone.order_management.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class CreateCategoryRequest {
    @Getter
    @Setter
    @NotBlank(message = "Category name is required")
    private String name;

    public CreateCategoryRequest() {
    }
}