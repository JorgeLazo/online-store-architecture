package cl.jorgelazo.onlinestore.inventory.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import java.util.List;

import cl.jorgelazo.onlinestore.inventory.services.InventoryService;
import cl.jorgelazo.onlinestore.inventory.model.dtos.BaseResponse;
import cl.jorgelazo.onlinestore.inventory.model.dtos.OrderItemRequest;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping ("/api/inventory")
@RequiredArgsConstructor 
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping ("/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable("sku") String skuCode) {
        return inventoryService.isInStock(skuCode);
    }

    @PostMapping("/in-stock")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse areInStock(@RequestBody List<OrderItemRequest> orderItemRequest) {
        return inventoryService.areInStock(orderItemRequest);
    }
}
