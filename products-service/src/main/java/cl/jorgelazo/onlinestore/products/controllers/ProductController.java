package cl.jorgelazo.onlinestore.products.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cl.jorgelazo.onlinestore.products.model.dtos.ProductRequest;
import cl.jorgelazo.onlinestore.products.model.dtos.ProductResponse;
import cl.jorgelazo.onlinestore.products.services.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController 
@RequestMapping ("api/product")
@RequiredArgsConstructor 
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public void addProduct(@RequestBody ProductRequest productRequest) {
        this.productService.addProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus (HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return this.productService.getAllProducts();
    }
}
