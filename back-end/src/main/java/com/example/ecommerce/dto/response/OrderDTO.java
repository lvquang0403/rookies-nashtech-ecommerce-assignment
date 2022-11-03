package com.example.ecommerce.dto.response;

import com.example.ecommerce.entity.Order;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long orderId;
    private String orderName;
    private String orderPhone;
    private Integer status;

    public static OrderDTO fromOrder(Order order){
        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .orderName(order.getOrderName())
                .orderPhone(order.getOrderPhone())
                .status(order.getOrderState())
                .build();
    }

}
