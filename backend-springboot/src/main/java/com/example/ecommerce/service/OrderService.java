package com.example.ecommerce.service;

import com.example.ecommerce.dto.request.OrderPostDTO;
import com.example.ecommerce.dto.response.ItemViewDTO;
import com.example.ecommerce.dto.response.ListOrderDTO;
import com.example.ecommerce.dto.response.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO createOrder(OrderPostDTO orderPostDTO);
    OrderDTO createOrderWithOneItem(OrderPostDTO orderPostDTO, Long productId, String color);
    ListOrderDTO getOrders(int pageNumber, int pageSize);
    ListOrderDTO getCustomerOrders(int pageNumber, int pageSize, Long customerId);
    List<ItemViewDTO> getOrderItemsByOrderId(Long orderId);
    void updateStatus(Long orderId, Integer status);
}
