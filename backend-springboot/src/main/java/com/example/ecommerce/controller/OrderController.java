package com.example.ecommerce.controller;

import com.example.ecommerce.dto.request.OrderPostDTO;
import com.example.ecommerce.dto.response.ItemViewDTO;
import com.example.ecommerce.dto.response.ListOrderDTO;
import com.example.ecommerce.dto.response.OrderDTO;
import com.example.ecommerce.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ListOrderDTO> getOrders(
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize
    ){
        return ResponseEntity.ok(orderService.getOrders(pageNumber, pageSize));
    }

    @GetMapping("/{orderId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<ItemViewDTO>> getOrdersItemById(@PathVariable Long orderId){
        return ResponseEntity.ok(orderService.getOrderItemsByOrderId(orderId));
    }

    @GetMapping("/customer-orders/{customerId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ListOrderDTO> getCustomerOrders(
            @PathVariable Long customerId,
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize
    ){
        return ResponseEntity.ok(orderService.getCustomerOrders(pageNumber, pageSize, customerId));
    }

    @PutMapping("/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateStatusOrder(@PathVariable Long orderId,
                                            @RequestParam Integer status)
    {   orderService.updateStatus(orderId,status);
        return ResponseEntity.ok("Successfully");
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody @Valid OrderPostDTO orderDTO){
        return ResponseEntity.ok(orderService.createOrder(orderDTO));
    }

    @PostMapping("create-one")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<OrderDTO> createOrderWithOneItem(
            @RequestParam Long productId,
            @RequestParam String color,
            @RequestBody @Valid OrderPostDTO orderDTO){
        return ResponseEntity.ok(orderService.createOrderWithOneItem(orderDTO, productId, color));
    }

}
