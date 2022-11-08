package com.example.ecommerce.controller;

import com.example.ecommerce.dto.request.CartItemPutDTO;
import com.example.ecommerce.dto.response.CartDTO;
import com.example.ecommerce.dto.request.ItemPostDTO;
import com.example.ecommerce.dto.response.ListCartItemDTO;
import com.example.ecommerce.service.CartItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("api/v1/carts")
public class CartController {
    private final CartItemService cartItemService;
    public CartController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping()
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<?> addProductToCart(
            @RequestBody @Valid ItemPostDTO item){
        cartItemService.addToCart(item);
        return ResponseEntity.ok("Successfully");
    }

    @GetMapping("cart-items/{customerId}")
    ResponseEntity<ListCartItemDTO> getCartItems(
            @PathVariable Long customerId,
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize)
    {
        return ResponseEntity.ok(cartItemService.findByCustomerId(pageNumber, pageSize, customerId));
    }

    @GetMapping("number-cart-items/{customerId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity getNumberCartItems(@PathVariable Long customerId){
        return ResponseEntity.ok(cartItemService.getNumberCartItemsByCustomerId(customerId));
    }

    @PutMapping("cart-item")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<?> updateCartItem(
            @RequestBody @Valid CartItemPutDTO item){
        cartItemService.updateCartItem(item);
        return ResponseEntity.ok("Successfully");
    }

    @DeleteMapping("/{cartItemId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<?> deleteById(@PathVariable Long cartItemId){
        cartItemService.deleteById(cartItemId);
        return ResponseEntity.ok("Successfully");
    }


}
