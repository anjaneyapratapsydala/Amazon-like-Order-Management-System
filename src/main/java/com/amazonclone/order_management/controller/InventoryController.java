package com.amazonclone.order_management.controller;

import com.amazonclone.order_management.dto.CreateInventoryRequest;
import com.amazonclone.order_management.dto.InventoryResponse;
import com.amazonclone.order_management.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(
            InventoryService inventoryService) {

        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<InventoryResponse> createInventory(
            @Valid @RequestBody
            CreateInventoryRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        inventoryService.createInventory(
                                request
                        )
                );
    }

    @GetMapping("/{productId}")
    public ResponseEntity<InventoryResponse> getInventory(
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                inventoryService.getInventory(productId)
        );
    }
}