package com.example.ecommerce.dto.response;

import com.example.ecommerce.entity.Order;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
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
