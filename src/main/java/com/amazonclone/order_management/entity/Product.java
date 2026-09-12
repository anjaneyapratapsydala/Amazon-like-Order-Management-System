package com.amazonclone.order_management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(
        name = "products",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "sku")
        }
)
public class Product {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(length = 2000)
    private String description;

    @Getter
    @Setter
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Getter
    @Setter
    @Column(nullable = false)
    private String sku;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "category_id",
            nullable = false
    )
    private Category category;

    public Product() {
    }

    public Product(
            String name,
            String description,
            BigDecimal price,
            String sku,
            Category category) {

        this.name = name;
        this.description = description;
        this.price = price;
        this.sku = sku;
        this.category = category;
    }
}