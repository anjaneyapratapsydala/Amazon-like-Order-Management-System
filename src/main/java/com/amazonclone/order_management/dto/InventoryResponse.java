package com.amazonclone.order_management.dto;


import lombok.Getter;
import lombok.Setter;

public class InventoryResponse {

    @Getter
    private Long productId;
    @Getter
    private int quantity;
    @Getter
    private int reservedQuantity;
    @Getter
    private int availableQuantity;

    public InventoryResponse(
            Long productId,
            int quantity,
            int reservedQuantity,
            int availableQuantity) {

        this.productId = productId;
        this.quantity = quantity;
        this.reservedQuantity = reservedQuantity;
        this.availableQuantity = availableQuantity;
    }
}
