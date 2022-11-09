package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.security.service.UserDetailsImpl;
import com.example.ecommerce.dto.request.CartItemPostDTO;
import com.example.ecommerce.dto.request.ItemPostDTO;
import com.example.ecommerce.dto.request.OrderPostDTO;
import com.example.ecommerce.dto.response.ItemViewDTO;
import com.example.ecommerce.dto.response.ListOrderDTO;
import com.example.ecommerce.dto.response.OrderDTO;
import com.example.ecommerce.dto.response.PageResponse;
import com.example.ecommerce.entity.*;
import com.example.ecommerce.exception.BadRequestException;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public OrderServiceImpl(CartItemRepository cartItemRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository, ImageRepository imageRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.cartItemRepository = cartItemRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.imageRepository = imageRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

//    @Override
//    @Transactional
//    public OrderDTO createOrder(OrderPostDTO orderDTO) {
//        Customer foundCustomer = customerRepository.findById(orderDTO.getCustomerId())
//                .orElseThrow(
//                        () -> new NotFoundException(String.format("Customer with id %s is not found", orderDTO.getCustomerId()))
//                );
//        Order newOrder = orderRepository.save(Order.builder()
//                .shipAddress(orderDTO.getAddress())
//                .orderState(0)
//                .orderPhone(orderDTO.getOrderPhone())
//                .totalPrice(orderDTO.getTotalPrice())
//                .customer(foundCustomer)
//                .build());
//        for (CartItemPostDTO item : orderDTO.getItems()) {
//            Product foundProduct = productRepository.findById(item.getProductId())
//                            .orElseThrow(() -> new NotFoundException("Product not found"));
//            orderItemRepository.save(new OrderItem(
//                    item.getQuantity(),
//                    item.getPrice(),
//                    item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())),
//                    item.getColor(),
//                    foundProduct,
//                    newOrder));
//        }
//        cartItemRepository.deleteAllByCartCustomerCustomerId(orderDTO.getCustomerId());
//        return OrderDTO.fromOrder(orderRepository.save(newOrder));
//    }
    @Override
    @Transactional
    public OrderDTO createOrder(OrderPostDTO orderDTO) {
        Customer foundCustomer = customerRepository.findById(orderDTO.getCustomerId())
                .orElseThrow(
                        () -> new NotFoundException(String.format("Customer with id %s is not found", orderDTO.getCustomerId()))
                );
        Date currentDate = Date.valueOf(LocalDate.now());
        Order newOrder = Order.builder()
                .shipAddress(orderDTO.getAddress())
                .orderState(0)
                .orderPhone(orderDTO.getOrderPhone())
                .totalPrice(orderDTO.getTotalPrice())
                .createDay(currentDate)
                .customer(foundCustomer)
                .build();
        Set<OrderItem> orderItemSet = new HashSet<>();
        for (CartItemPostDTO item : orderDTO.getItems()) {
            Product foundProduct = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new NotFoundException("Product not found"));
            orderItemSet.add(new OrderItem(
                    item.getQuantity(),
                    item.getPrice(),
                    item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())),
                    item.getColor(),
                    foundProduct,
                    newOrder));
        }
        newOrder.setOrderItems(orderItemSet);
        cartItemRepository.deleteAllByCartCustomerCustomerId(orderDTO.getCustomerId());
        return OrderDTO.fromOrder(orderRepository.save(newOrder));
    }


//    @Override
//    @Transactional
//    public OrderDTO createOrderWithOneItem(OrderPostDTO orderDTO, Long productId, String color) {
//        Customer foundCustomer = customerRepository.findById(orderDTO.getCustomerId())
//                .orElseThrow(
//                        () -> new NotFoundException(String.format("Customer with id %s is not found", orderDTO.getCustomerId()))
//                );
//        Product foundProduct = productRepository.findById(productId).orElseThrow(
//                () -> new NotFoundException(String.format("Product with id %s is not found", productId))
//        );
//        Order newOrder = orderRepository.save(Order.builder()
//                .shipAddress(orderDTO.getAddress())
//                .orderState(0)
//                .orderPhone(orderDTO.getOrderPhone())
//                .totalPrice(orderDTO.getTotalPrice())
//                .customer(foundCustomer)
//                .build());
//        orderItemRepository.save(new OrderItem(
//                1,
//                foundProduct.getPrice(),
//                foundProduct.getPrice(),
//                color,
//                foundProduct,
//                newOrder));
//        return OrderDTO.fromOrder(orderRepository.save(newOrder));
//    }
    @Override
    @Transactional
    public OrderDTO createOrderWithOneItem(OrderPostDTO orderDTO, Long productId, String color) {
        Customer foundCustomer = customerRepository.findById(orderDTO.getCustomerId())
                .orElseThrow(
                        () -> new NotFoundException(String.format("Customer with id %s is not found", orderDTO.getCustomerId()))
                );
        Product foundProduct = productRepository.findById(productId).orElseThrow(
                () -> new NotFoundException(String.format("Product with id %s is not found", productId))
        );
        Date currentDate = Date.valueOf(LocalDate.now());
        Order newOrder = Order.builder()
                .shipAddress(orderDTO.getAddress())
                .orderState(0)
                .orderPhone(orderDTO.getOrderPhone())
                .totalPrice(orderDTO.getTotalPrice())
                .createDay(currentDate)
                .customer(foundCustomer)
                .build();
        Set<OrderItem> orderItemSet = new HashSet<>();
        orderItemSet.add(new OrderItem(
                1,
                foundProduct.getPrice(),
                foundProduct.getPrice(),
                color,
                foundProduct,
                newOrder));
        newOrder.setOrderItems(orderItemSet);
        return OrderDTO.fromOrder(orderRepository.save(newOrder));
    }

    @Override
    public ListOrderDTO getOrders(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("orderId"));
        Page<Order> orderPage = orderRepository.findAll(pageable);
        List<Order> orders = orderPage.getContent();
        List<OrderDTO> orderDTOS = orders.stream()
                .map(OrderDTO::fromOrder)
                .collect(Collectors.toList());
        PageResponse pageResponse = new PageResponse(pageSize, pageNumber, orderPage.getTotalPages());
        return new ListOrderDTO(
                pageResponse,
                orderDTOS
        );
    }

    @Override
    public ListOrderDTO getCustomerOrders(int pageNumber, int pageSize, Long customerId) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("orderId"));
        Page<Order> orderPage = orderRepository.findByCustomerCustomerId(customerId, pageable);
        List<Order> orders = orderPage.getContent();
        List<OrderDTO> orderDTOS = orders.stream()
                .map(OrderDTO::fromOrder)
                .collect(Collectors.toList());
        PageResponse pageResponse = new PageResponse(pageSize, pageNumber, orderPage.getTotalPages());
        return new ListOrderDTO(
                pageResponse,
                orderDTOS
        );
    }

    @Override
    public List<ItemViewDTO> getOrderItemsByOrderId(Long orderId) {
        if (orderRepository.findById(orderId).isEmpty()) {
            throw new NotFoundException(String.format("Order with id %s is not found", orderId));
        }
        List<OrderItem> orderItems = orderItemRepository.findByOrderOrderId(orderId);
        List<ItemViewDTO> itemViewDTOS = new ArrayList<>();
        for (OrderItem item : orderItems) {
            Image image = imageRepository.findByColorIgnoreCaseAndProductProductId(item.getColor(), item.getProduct().getProductId())
                    .orElse(null);
            assert image != null;
            itemViewDTOS.add(new ItemViewDTO(
                    item.getOrderItemId(),
                    image.getUrl(),
                    item.getProduct().getProductName(),
                    item.getPrice(),
                    item.getQuantity(),
                    item.getColor(),
                    item.getTotalPrice(),
                    item.getProduct().getProductId(),
                    item.getProduct().getCategory().getCategoryId()
            ));
        }
        return itemViewDTOS;
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
