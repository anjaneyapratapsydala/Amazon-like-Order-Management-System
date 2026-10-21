package com.amazonclone.order_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "inventory",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "product_id")
        }
)
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "product_id",
            nullable = false,
            unique = true
    )
    @Getter
    private Product product;

    @Setter
    @Getter
    @Column(nullable = false)
    private int quantity;

    @Setter
    @Getter
    @Column(nullable = false)
    private int reservedQuantity;

    public Inventory(
            Product product,
            int quantity) {

        this.product = product;
        this.quantity = quantity;
        this.reservedQuantity = 0;
    }

    public int getAvailableQuantity() {
        return quantity - reservedQuantity;
    }
}