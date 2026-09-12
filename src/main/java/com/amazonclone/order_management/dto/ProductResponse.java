package com.amazonclone.order_management.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class ProductResponse {
    @Getter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private BigDecimal price;

    @Getter
    @Setter
    private String sku;

    @Getter
    @Setter
    private Long categoryId;

    @Getter
    @Setter
    private String categoryName;

    public ProductResponse(Long id,
                           String name,
                           String description,
                           BigDecimal price,
                           String sku,
                           Long categoryId,
                           String categoryName)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.sku = sku;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
