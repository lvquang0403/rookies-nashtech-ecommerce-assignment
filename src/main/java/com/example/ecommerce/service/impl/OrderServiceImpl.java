package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.security.service.UserDetailsImpl;
import com.example.ecommerce.dto.request.OrderPostDTO;
import com.example.ecommerce.dto.response.ItemViewDTO;
import com.example.ecommerce.dto.response.ListOrderDTO;
import com.example.ecommerce.dto.response.OrderDTO;
import com.example.ecommerce.entity.*;
import com.example.ecommerce.exception.BadRequestException;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.service.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderServiceImpl(CartItemRepository cartItemRepository, CartRepository cartRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    @Transactional
    public OrderDTO createOrder(OrderPostDTO orderDTO) {
        UserDetailsImpl userDetails =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long customerId = userDetails.getCustomerId();
        Cart cart = cartRepository.findByCustomerCustomerId(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        List<CartItem> cartItems = cartItemRepository.findCartItemByCartCartId(cart.getCartId());
        if(cartItems.isEmpty()){
            throw new BadRequestException("don't any product in Cart");
        }
        Order newOrder = orderRepository.save(Order.builder()
                        .orderName(orderDTO.getOrderName())
                        .shipAddress(orderDTO.getAddress())
                        .orderState(0)
                        .orderPhone(orderDTO.getOrderPhone())
                        .totalPrice(orderDTO.getTotalPrice())
                .build());
        Set<OrderItem> setCartItems = cartItems.stream()
                .map(item -> new OrderItem(
                        item.getQuantity(),
                        item.getPrice(),
                        item.getTotalPrice(),
                        item.getProduct(),
                        newOrder
                )).collect(Collectors.toSet());
        newOrder.setOrderItems(setCartItems);
        cartRepository.deleteById(cart.getCartId());
        return OrderDTO.fromOrder(orderRepository.save(newOrder));
    }

    @Override
    public ListOrderDTO getOrders(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Order> orders = orderRepository.findAll(pageable).getContent();
        if(orders.isEmpty()){
            throw new NotFoundException("don't have any order");
        }
        List<OrderDTO> orderDTOS = orders.stream()
                .map(OrderDTO::fromOrder)
                .toList();

        return  new ListOrderDTO(
                pageNumber,
                pageSize,
                orderDTOS
        );
    }

    @Override
    public ListOrderDTO getCustomerOrders(int pageNumber, int pageSize) {
        UserDetailsImpl userDetails =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long customerId = userDetails.getCustomerId();
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Order> orders = orderRepository.findByCustomerCustomerId(customerId,pageable).getContent();
        if(orders.isEmpty()){
            throw new NotFoundException("don't have any order");
        }
        List<OrderDTO> orderDTOS = orders.stream()
                .map(OrderDTO::fromOrder)
                .toList();
        return  new ListOrderDTO(
                pageNumber,
                pageSize,
                orderDTOS
        );
    }

    @Override
    public List<ItemViewDTO> getOrderItemsByOrderId(Long orderId) {
        if(orderRepository.findById(orderId).isEmpty()) {
            throw new NotFoundException(String.format("Order with id %s is not found", orderId));
        }
        List<OrderItem> orderItems = orderItemRepository.findByOrderOrderId(orderId);

        return orderItems.stream()
                .map(orderItem -> new ItemViewDTO(
                        orderItem.getOrderItemId(),
                        orderItem.getProduct().getProductName(),
                        orderItem.getPrice(),
                        orderItem.getQuantity(),
                        orderItem.getTotalPrice()
                )).toList();

    }

    @Override
    public void updateStatus(Long orderId, Integer status) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new NotFoundException(String.format("Order with id %s is not found", orderId))
        );
        order.setOrderState(status);
        orderRepository.save(order);
    }
}