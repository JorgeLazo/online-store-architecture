package cl.jorgelazo.onlinestore.orders.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import cl.jorgelazo.onlinestore.orders.model.dtos.BaseResponse;
import cl.jorgelazo.onlinestore.orders.model.dtos.OrderItemRequest;
import cl.jorgelazo.onlinestore.orders.model.dtos.OrderRequest;
import cl.jorgelazo.onlinestore.orders.model.entities.Order;
import cl.jorgelazo.onlinestore.orders.model.entities.OrderItems;
import cl.jorgelazo.onlinestore.orders.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class OrderService {

    private final OrderRepository orderRepository;

    private final WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequest orderRequest) {

        BaseResponse baseResponse = this.webClientBuilder.build().post()
        .uri("http://localhost:8083/api/inventory/in-stock")
        .bodyValue(orderRequest.getOrderItems())
        .retrieve()
        .bodyToMono(BaseResponse.class)
        .block();

    
        if (baseResponse != null && !baseResponse.hasErrors()) {
            
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setOrderItems(orderRequest.getOrderItems()
            .stream()
            .map(orderItemRequest -> mapOrderItemRequestToOrderItem(orderItemRequest, order)).toList());
            
            this.orderRepository.save(order);
            
        } else {
            throw new IllegalArgumentException("Some items are not in stock");
        }
    }


    private OrderItems mapOrderItemRequestToOrderItem(OrderItemRequest orderItemRequest, Order order) {
        return OrderItems.builder()
        .id(orderItemRequest.getId())
        .sku(orderItemRequest.getSku())
        .price(orderItemRequest.getPrice())
        .quantity(orderItemRequest.getQuantity())
        .order(order)
        .build();
    }
}
