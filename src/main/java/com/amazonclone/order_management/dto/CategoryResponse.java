package com.amazonclone.order_management.dto;

import lombok.Getter;

public class CategoryResponse {

    @Getter
    private Long id;

    @Getter
    private String name;

    public CategoryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}