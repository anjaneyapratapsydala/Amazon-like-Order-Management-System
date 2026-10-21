package com.amazonclone.order_management.service;

import com.amazonclone.order_management.dto.CreateInventoryRequest;
import com.amazonclone.order_management.dto.InventoryResponse;
import com.amazonclone.order_management.entity.Inventory;
import com.amazonclone.order_management.entity.Product;
import com.amazonclone.order_management.repository.InventoryRepository;
import com.amazonclone.order_management.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    public InventoryService(
            InventoryRepository inventoryRepository,
            ProductRepository productRepository) {

        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public InventoryResponse createInventory(
            CreateInventoryRequest request) {

        if (inventoryRepository
                .existsByProductId(request.getProductId())) {

            throw new RuntimeException(
                    "Inventory already exists for product"
            );
        }

        Product product =
                productRepository.findById(
                        request.getProductId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Product not found"
                        )
                );

        Inventory inventory =
                new Inventory(
                        product,
                        request.getQuantity()
                );

        Inventory saved =
                inventoryRepository.save(inventory);

        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public InventoryResponse getInventory(
            Long productId) {

        Inventory inventory =
                inventoryRepository
                        .findByProductId(productId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Inventory not found"
                                )
                        );

        return toResponse(inventory);
    }

    @Transactional
    public void reserveInventory(
            Long productId,
            int quantity) {

        Inventory inventory =
                inventoryRepository
                        .findByProductIdForUpdate(productId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Inventory not found"
                                )
                        );

        int available =
                inventory.getAvailableQuantity();

        if (available < quantity) {
            throw new RuntimeException(
                    "Insufficient inventory"
            );
        }

        inventory.setReservedQuantity(
                inventory.getReservedQuantity() + quantity
        );

        inventoryRepository.save(inventory);
    }
    private InventoryResponse toResponse(
            Inventory inventory) {

        return new InventoryResponse(
                inventory.getProduct().getId(),
                inventory.getQuantity(),
                inventory.getReservedQuantity(),
                inventory.getAvailableQuantity()
        );
    }
}