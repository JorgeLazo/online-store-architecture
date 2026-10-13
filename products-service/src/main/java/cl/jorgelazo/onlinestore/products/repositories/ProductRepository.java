package cl.jorgelazo.onlinestore.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.jorgelazo.onlinestore.products.model.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
