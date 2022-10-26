package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CartDTO;
import com.example.ecommerce.dto.ItemPostDTO;
import com.example.ecommerce.dto.ListCartItemDTO;
import com.example.ecommerce.service.CartItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cart")
public class CartController {
    private final CartItemService cartItemService;

    public CartController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }


    @PostMapping
    ResponseEntity<CartDTO> addProductToCart(@RequestBody ItemPostDTO item){
        return ResponseEntity.ok(cartItemService.addToCart(item));
    }

    @GetMapping("getByCustomerId/{customerId}")
    ResponseEntity<ListCartItemDTO> getByCustomerId(
            @PathVariable Long customerId,
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize)
    {
        return ResponseEntity.ok(cartItemService.findByCustomerId(pageNumber, pageSize, customerId));
    }

    @PutMapping("updateByCustomerId/{customerId}")
    ResponseEntity<List<ItemPostDTO>> updateCartItem(
            @PathVariable Long customerId,
            @RequestBody List<ItemPostDTO> items){
        return ResponseEntity.ok(cartItemService.updateCartItemByCustomerId(customerId,items));
    }

    @DeleteMapping("/{cartItemId}")
    ResponseEntity<?> deleteById(@PathVariable Long cartItemId){
        return ResponseEntity.ok("Successfully");
    }


}
