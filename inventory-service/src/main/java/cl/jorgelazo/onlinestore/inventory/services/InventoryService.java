package cl.jorgelazo.onlinestore.inventory.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.jorgelazo.onlinestore.inventory.model.dtos.BaseResponse;
import cl.jorgelazo.onlinestore.inventory.model.dtos.OrderItemRequest;
import cl.jorgelazo.onlinestore.inventory.model.entities.Inventory;
import cl.jorgelazo.onlinestore.inventory.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public boolean isInStock(String sku) {
        return inventoryRepository.findBySku(sku)
                .map(inventory -> inventory.getQuantity() > 0)
                .orElse(false);
    }

    public BaseResponse areInStock(List<OrderItemRequest> orderItemRequest) {
        
        var errorList = new ArrayList<String>();

        List<String> skus = orderItemRequest.stream()
                .map(OrderItemRequest::getSku)
                .toList();
        
        List<Inventory> inventoryList = inventoryRepository.findBySkuIn(skus);

        orderItemRequest.forEach(orderItem -> {
            var inventory = inventoryList.stream()
                    .filter(inv -> inv.getSku().equals(orderItem.getSku()))
                    .findFirst();

            if (inventory.isEmpty()) {
                errorList.add("Product with SKU " + orderItem.getSku() + " does not exist.");
            }
            else if (inventory.get().getQuantity() < orderItem.getQuantity()) {
                errorList.add("Product with SKU " + orderItem.getSku() + " has insufficient quantity.");
            }
        });
        return errorList.size() > 0 ? new BaseResponse(errorList.toArray(new String[0])) : new BaseResponse(null);
    }
}
