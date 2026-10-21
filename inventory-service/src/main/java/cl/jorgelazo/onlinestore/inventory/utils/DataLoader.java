package cl.jorgelazo.onlinestore.inventory.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import cl.jorgelazo.onlinestore.inventory.model.entities.Inventory;
import cl.jorgelazo.onlinestore.inventory.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component 
@RequiredArgsConstructor
@Slf4j 
public class DataLoader implements CommandLineRunner {

    private final InventoryRepository inventoryRepository;

    @Override
    public void run(String... args) throws Exception {
        
        if (inventoryRepository.count() == 0) {
            log.info("Loading inventory data...");
            Inventory item1 = Inventory.builder()
                    .sku("000001")
                    .quantity(100L)
                    .build();

            Inventory item2 = Inventory.builder()
                    .sku("000002")
                    .quantity(50L)
                    .build();

            Inventory item3 = Inventory.builder()
                    .sku("000003")
                    .quantity(30L)
                    .build();

            Inventory item4 = Inventory.builder()
                    .sku("000004")
                    .quantity(0L)
                    .build();

            inventoryRepository.save(item1);
            inventoryRepository.save(item2);
            inventoryRepository.save(item3);
            inventoryRepository.save(item4);

            log.info("Inventory data loaded.");
        } else {
            log.info("Inventory data already exists. Skipping data loading.");
        }
    }


}
