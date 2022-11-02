package com.example.ecommerce.dto.response;

import com.example.ecommerce.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long orderId;
    private String orderName;
    private String orderPhone;
    private Integer status;

    public static OrderDTO fromOrder(Order order){
        return new OrderDTO(
                order.getOrderId(),
                order.getOrderName(),
                order.getOrderPhone(),
                order.getOrderState()
        );
    }

}
