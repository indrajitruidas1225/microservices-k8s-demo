package com.microservices.cart_service.controller;

import com.microservices.cart_service.dto.CartRequest;
import com.microservices.cart_service.entity.CartItem;
import com.microservices.cart_service.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public CartItem addToCart(@RequestBody CartRequest request){
        return cartService.addToCart(request);
    }

    @GetMapping("/{userId}")
    public List<CartItem> getCart(@PathVariable Long userId){
        return cartService.getCart(userId);
    }
}
