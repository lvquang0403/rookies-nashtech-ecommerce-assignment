package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.security.service.UserDetailsImpl;
import com.example.ecommerce.dto.request.CartItemPutDTO;
import com.example.ecommerce.dto.response.CartDTO;
import com.example.ecommerce.dto.request.ItemPostDTO;
import com.example.ecommerce.dto.response.ItemViewDTO;
import com.example.ecommerce.dto.response.ListCartItemDTO;
import com.example.ecommerce.entity.*;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.service.CartItemService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final ImageRepository imageRepository;

    public CartItemServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository, CustomerRepository customerRepository, ImageRepository imageRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public ListCartItemDTO findByCustomerId(int pageNumber, int pageSize, Long customerId) {
        Customer foundCustomer = customerRepository.findById(customerId)
                .orElseThrow(
                        () -> new NotFoundException(String.format("Customer with id %s is not found", customerId))
                );
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("cartItemId"));
        List<ItemViewDTO> items = cartItemRepository.findCartItemByCartCustomerCustomerId(pageable ,foundCustomer.getCustomerId())
                .stream()
                .map(cartItem -> {
                    Optional<Image> image = imageRepository.findByColorIgnoreCaseAndProductProductId(
                            cartItem.getColor(),
                            cartItem.getProduct().getProductId());
                    String urlImage = null;
                    if (image.isPresent()) {
                        urlImage = image.get().getUrl();
                    }
                    return new ItemViewDTO(
                            cartItem.getCartItemId(),
                            urlImage,
                            cartItem.getProduct().getProductName(),
                            cartItem.getPrice(),
                            cartItem.getQuantity(),
                            cartItem.getColor(),
                            cartItem.getTotalPrice(),
                            cartItem.getProduct().getProductId(),
                            cartItem.getProduct().getCategory().getCategoryId()
                    );
                }).collect(Collectors.toList());

        return new ListCartItemDTO(pageNumber, pageSize, items);
    }

    @Override
    public void addToCart(ItemPostDTO itemDTO) {
        Product foundProduct = productRepository.findById(itemDTO.getProductId()).orElseThrow(
                () -> new NotFoundException(String.format("Product with id %s is not found", itemDTO.getProductId()))
        );
        Customer foundCustomer = customerRepository.findById(itemDTO.getCustomerId()).orElseThrow(
                () -> new NotFoundException(String.format("Customer with id %s is not found", itemDTO.getProductId()))
        );
        Date currentDay = Date.valueOf(LocalDate.now());
        Cart cart = cartRepository.findByCustomerCustomerId(itemDTO.getCustomerId())
                .orElseGet(() -> Cart.builder()
                            .createdDate(currentDay)
                            .customer(foundCustomer)
                            .build());
        foundCustomer.setCart(cart);
        customerRepository.save(foundCustomer);
        CartItem foundCartItem = cartItemRepository.findByCartCartIdAndProductProductIdAndColor(
                foundCustomer.getCart().getCartId(),
                        itemDTO.getProductId(),
                        itemDTO.getColor()
                )
                .orElse(null);
        //Check Product have correct color
        if (imageRepository.findByColorIgnoreCaseAndProductProductId(itemDTO.getColor(), foundProduct.getProductId()).isEmpty()) {
            throw new NotFoundException(String.format("Product with id %s don't any %s color", foundProduct.getProductId(), itemDTO.getColor()));
        }
        if (foundCartItem == null) {
            foundCartItem = new CartItem(
                    1,
                    foundProduct.getPrice(),
                    foundProduct.getPrice(),
                    itemDTO.getColor(),
                    foundProduct,
                    foundCustomer.getCart()
            );
        } else {
            foundCartItem.setQuantity(foundCartItem.getQuantity() + 1);
            foundCartItem.setTotalPrice(foundCartItem.getPrice().multiply(BigDecimal.valueOf(foundCartItem.getQuantity())));
        }
        cartItemRepository.save(foundCartItem);
    }

    @Override
    public Integer getNumberCartItemsByCustomerId(Long customerId) {
        Customer foundCustomer = customerRepository.findById(customerId)
                .orElseThrow(
                        () -> new NotFoundException(String.format("Customer with id %s is not found", customerId))
                );
        return cartItemRepository.sumByCustomerId(foundCustomer.getCustomerId());
    }


    @Override
    public void updateCartItem(CartItemPutDTO item) {
        UserDetailsImpl userDetails =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long customerId = userDetails.getCustomerId();
        Cart cart = cartRepository.findByCustomerCustomerId(customerId).orElseThrow(
                () -> new NotFoundException(String.format("Cart of customer with id %s not exists", customerId))
        );
        CartItem foundCartItem = cartItemRepository.findByCartCartIdAndCartItemId(cart.getCartId(), item.getCartItemId())
                .orElseThrow(
                        () -> new NotFoundException(String.format("Not found CartItem with id %s in Cart of Customer", item.getCartItemId()))
                );
        foundCartItem.setQuantity(item.getQuantity());
        foundCartItem.setTotalPrice(foundCartItem.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        cartItemRepository.save(foundCartItem);
    }

    @Override
    public void deleteById(Long cartItemId) {
        UserDetailsImpl userDetails =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long customerId = userDetails.getCustomerId();
        Cart cart = cartRepository.findByCustomerCustomerId(customerId).orElseThrow(
                () -> new NotFoundException(String.format("Cart of customer with id %s not exists", customerId))
        );
        CartItem foundCartItem = cartItemRepository.findByCartCartIdAndCartItemId(cart.getCartId(), cartItemId)
                .orElseThrow(
                        () -> new NotFoundException(String.format("Not found CartItem with id %s in Cart of Customer", cartItemId))
                );
        cartItemRepository.delete(foundCartItem);
    }
}
