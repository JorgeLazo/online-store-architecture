package cl.jorgelazo.onlinestore.orders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.jorgelazo.onlinestore.orders.model.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
