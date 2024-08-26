package br.com.devsenior.dscommerce.dto;

import br.com.devsenior.dscommerce.entities.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    
    private Long productId;
    private String name;
    private Double price;
    private Integer quantity;

    public OrderItemDTO(OrderItem entity) {
        productId = entity.geProduct().getId();
        name = entity.geProduct().getName();
        price = entity.getPrice();
        quantity = entity.getQuantity();
    }
    
    public Double getSubTotal() {
        return price * quantity;
    }
}
