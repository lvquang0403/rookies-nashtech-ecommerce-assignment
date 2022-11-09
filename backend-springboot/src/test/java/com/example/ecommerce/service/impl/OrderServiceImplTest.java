package com.example.ecommerce.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import com.example.ecommerce.dto.request.CartItemPostDTO;
import com.example.ecommerce.dto.request.OrderPostDTO;
import com.example.ecommerce.dto.response.ListOrderDTO;
import com.example.ecommerce.dto.response.OrderDTO;
import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.CartItemRepository;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.repository.ImageRepository;
import com.example.ecommerce.repository.OrderItemRepository;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

class OrderServiceImplTest {

    private OrderServiceImpl orderService;
    private CartItemRepository cartItemRepository;
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private ImageRepository imageRepository;
    private ProductRepository productRepository;
    private CustomerRepository customerRepository;

    @BeforeEach
    void beforeEach() {
        cartItemRepository = Mockito.mock(CartItemRepository.class);
        orderRepository = Mockito.mock(OrderRepository.class);
        orderItemRepository = Mockito.mock(OrderItemRepository.class);
        imageRepository = Mockito.mock(ImageRepository.class);
        productRepository = Mockito.mock(ProductRepository.class);
        customerRepository = Mockito.mock(CustomerRepository.class);
        orderService = new OrderServiceImpl(cartItemRepository, orderRepository, orderItemRepository,
                imageRepository, productRepository, customerRepository);
    }

    @Test
    void testCreateOrderWhenValidShouldReturnNewOrder() {
        Long customerId = 1234L;
        Customer customer = Mockito.mock(Customer.class);
        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        Long productId = 1321L;
        Product product = Mockito.mock(Product.class);
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        OrderPostDTO orderPostDTO = OrderPostDTO.builder().orderPhone("012345678").address("address")
                .customerName("customer-name").totalPrice(BigDecimal.TEN).customerId(customerId)
                .items(List.of(
                        CartItemPostDTO.builder().productId(productId).color("color").price(BigDecimal.ONE)
                                .quantity(1).build())
                ).build();
        Mockito.when(orderRepository.save(any(Order.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        OrderDTO actual = orderService.createOrder(orderPostDTO);

        assertThat(actual.getOrderPhone()).isEqualTo("012345678");
        assertThat(actual.getTotalPrice()).isEqualTo(BigDecimal.TEN);
        assertThat(actual.getShipAddress()).isEqualTo("address");
    }

    @Test
    void testGetOrders() {
        Page<Order> orderPage = Mockito.mock(Page.class);
        Mockito.when(orderRepository.findAll(PageRequest.of(1, 2, Sort.by("orderId"))))
                .thenReturn(orderPage);
        List<Order> orders = List.of(Order.builder().orderId(1L).orderState(1)
                .orderPhone("phone").orderName("order-name").totalPrice(BigDecimal.TEN).build());
        Mockito.when(orderPage.getContent()).thenReturn(orders);
        Mockito.when(orderPage.getTotalPages()).thenReturn(3);

        ListOrderDTO actual = orderService.getOrders(1, 2);

        assertThat(actual.getOrders()).hasSize(1);
        assertThat(actual.getPageResponse().getPageNumber()).isEqualTo(1);
        assertThat(actual.getPageResponse().getPageSize()).isEqualTo(2);
        assertThat(actual.getPageResponse().getTotalPage()).isEqualTo(3);
    }
}
