package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.CartDTO;
import com.example.ecommerce.dto.ItemPostDTO;
import com.example.ecommerce.dto.response.ItemViewDTO;
import com.example.ecommerce.dto.ListCartItemDTO;
import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.CartItemRepository;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.CartItemService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public CartItemServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public ListCartItemDTO findByCustomerId(int pageNumber, int pageSize, Long customerId) {
        Customer foundCustomer = customerRepository.findById(customerId)
                .orElseThrow(
                        () -> new NotFoundException(String.format("Customer with id %s is not found", customerId))
                );
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        List<ItemViewDTO> items = cartItemRepository.findCartItemByCartCartId(pageable,foundCustomer.getCart().getCartId())
                .stream()
                .map(cartItem -> new ItemViewDTO(
                        cartItem.getCartItemId(),
                        null,
                        cartItem.getProduct().getProductName(),
                        cartItem.getPrice(),
                        cartItem.getQuantity(),
                        cartItem.getTotalPrice()
                )).toList();
        return new ListCartItemDTO(pageNumber, pageSize, items);
    }

    @Override
    public CartDTO addToCart(ItemPostDTO itemDTO) {
        Product foundProduct = productRepository.findById(itemDTO.getProductId()).orElseThrow(
                () -> new NotFoundException(String.format("Product with id %s is not found", itemDTO.getProductId()))
        );
        Date currentDay = Date.valueOf(LocalDate.now());
        Cart cart = cartRepository.findByCustomerCustomerId(itemDTO.getCustomerId())
                .orElseGet(() -> {
                     Cart newCart = cartRepository.save(Cart.builder()
                                    .createdDate(currentDay)
                                    .customer(customerRepository.findById(itemDTO.getCustomerId()).get())
                                    .build());
                    Optional<Customer> foundCustomer = customerRepository.findById(itemDTO.getCustomerId());
                    foundCustomer.ifPresent(customer -> customer.setCart(newCart));
                    return customerRepository.save(foundCustomer.get()).getCart();
                });

        CartItem foundCartItem = cartItemRepository.findByCartCartIdAndProductProductId(cart.getCartId(), itemDTO.getProductId())
                .orElse(null);
        if(foundCartItem == null){
            foundCartItem = new CartItem(
                    1,
                    foundProduct.getPrice(),
                    foundProduct.getPrice(),
                    foundProduct,
                    cart
            );
        }
        else {
            foundCartItem.setQuantity(foundCartItem.getQuantity()+1);
            foundCartItem.setTotalPrice(foundCartItem.getPrice().multiply(BigDecimal.valueOf(foundCartItem.getQuantity())));
        }
        cartItemRepository.save(foundCartItem);

        return new CartDTO(cart.getCartId(), currentDay);
    }


    @Override
    public List<ItemPostDTO> updateCartItemByCustomerId(Long customerId, List<ItemPostDTO> items) {
        Cart cart = cartRepository.findByCustomerCustomerId(customerId).orElseThrow(
                () -> new NotFoundException(String.format("Cart of customer with id %s not exists", customerId))
        );
        List<ItemPostDTO> listItemDTO = new ArrayList<>();
        for(ItemPostDTO item : items) {
            CartItem foundCartItem = cartItemRepository.findByCartCartIdAndProductProductId(cart.getCartId(), item.getProductId())
                    .orElseThrow(
                            () -> new NotFoundException(String.format("Not found CartItem with productId %s in Cart",item.getProductId()))
                    );
            foundCartItem.setQuantity(item.getQuantity());
            foundCartItem.setTotalPrice(foundCartItem.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            CartItem newCartItem = cartItemRepository.save(foundCartItem);
            listItemDTO.add(ItemPostDTO.builder()
                    .productId(newCartItem.getProduct().getProductId())
                    .quantity(newCartItem.getQuantity())
                    .build());
        }
        return listItemDTO;
    }

    @Override
    public void deleteById(Long cartItemId) {
        CartItem foundCartItem = cartItemRepository.findById(cartItemId).orElseThrow(
                () -> new NotFoundException(String.format("Cart item with id %s is not found", cartItemId))
        );
        cartRepository.deleteById(foundCartItem.getCartItemId());
    }
}
