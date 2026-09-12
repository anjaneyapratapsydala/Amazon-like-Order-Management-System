package com.amazonclone.order_management.dto;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class CreateProductRequest {

    @Getter
    @Setter
    @NotBlank(message = "Product name is required")
    private String name;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    @NotNull(message = "Price is required")
    @DecimalMin(
            value = "0.01",
            message = "Price must be greater than 0"
    )
    private BigDecimal price;


    @Getter
    @Setter
    @NotBlank(message = "SKU is required")
    private String sku;

    @Getter
    @Setter
    @NotNull(message = "Category ID is required")
    private Long categoryId;

    public CreateProductRequest() {
    }
}