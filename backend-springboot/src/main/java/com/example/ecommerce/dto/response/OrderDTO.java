package com.example.ecommerce.dto.response;

import com.example.ecommerce.entity.Order;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long orderId;
    private BigDecimal totalPrice;
    private String shipAddress;
    private String orderPhone;
    private Date createDay;
    private Integer status;

    public static OrderDTO fromOrder(Order order){
        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .orderPhone(order.getOrderPhone())
                .status(order.getOrderState())
                .shipAddress(order.getShipAddress())
                .totalPrice(order.getTotalPrice())
                .createDay(order.getCreateDay())
                .build();
    }

}
