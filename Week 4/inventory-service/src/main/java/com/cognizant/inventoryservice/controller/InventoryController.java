package com.cognizant.inventoryservice.controller;

import com.cognizant.inventoryservice.entity.Inventory;
import com.cognizant.inventoryservice.repository.InventoryRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryRepository inventoryRepository;

    public InventoryController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @GetMapping("/{productCode}")
    public Inventory getInventoryByProductCode(@PathVariable String productCode) {
        return inventoryRepository.findByProductCode(productCode)
                .orElseThrow(() -> new RuntimeException("Inventory not found for product code: " + productCode));
    }

    @PostMapping
    public Inventory saveInventory(@RequestBody Inventory inventory) {
        return inventoryRepository.save(inventory);
    }
}
