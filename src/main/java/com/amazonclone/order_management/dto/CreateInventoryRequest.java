package com.amazonclone.order_management.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class CreateInventoryRequest {

    @NotNull
    @Getter
    @Setter
    private Long productId;

    @Getter
    @Setter
    @Min(value = 0, message = "Quantity cannot be negative")
    private int quantity;

    public CreateInventoryRequest() {
    }
}