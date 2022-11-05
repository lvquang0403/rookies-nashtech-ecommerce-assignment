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
@RequestMapping("api/v1/cart")
public class CartController {
    private final CartItemService cartItemService;

    public CartController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }


    @PostMapping()
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<CartDTO> addProductToCart(@RequestBody @Valid ItemPostDTO item){
        return ResponseEntity.ok(cartItemService.addToCart(item));
    }

    @GetMapping("cart-items")
    ResponseEntity<ListCartItemDTO> getCartItems(
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize)
    {
        return ResponseEntity.ok(cartItemService.findByCustomerId(pageNumber, pageSize));
    }

    @GetMapping("number-cart-items")
    ResponseEntity getNumberCartItems(){
        return ResponseEntity.ok(cartItemService.getNumberCartItemsByCustomerId());
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