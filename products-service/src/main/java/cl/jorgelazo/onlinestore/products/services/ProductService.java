package cl.jorgelazo.onlinestore.products.services;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.jorgelazo.onlinestore.products.model.dtos.ProductRequest;
import cl.jorgelazo.onlinestore.products.model.dtos.ProductResponse;
import cl.jorgelazo.onlinestore.products.model.entities.Product;
import cl.jorgelazo.onlinestore.products.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service 
@RequiredArgsConstructor 
@Slf4j 
public class ProductService {

    private final ProductRepository productRepository;

    public void addProduct(ProductRequest productRequest) {
        log.info("Adding product: {}", productRequest);
        var product = Product.builder()
                .sku(productRequest.getSku())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .status(productRequest.getStatus())
                .build();

        productRepository.save(product);

        log.info("Product added successfully: {}", product);
    }

    public List<ProductResponse> getAllProducts() {
        log.info("Fetching all products");
        var products = productRepository.findAll();

        return products.stream()
        .map(this::mapToProductResponse)
        .toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .sku(product.getSku())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .status(product.getStatus())
                .build();
    }
}
