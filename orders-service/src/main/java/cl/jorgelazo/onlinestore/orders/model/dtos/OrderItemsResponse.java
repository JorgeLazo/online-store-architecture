package cl.jorgelazo.onlinestore.orders.model.dtos;

public record OrderItemsResponse(
    Long id,
    String sku,
    Double price,
    Long quantity
) {

}
