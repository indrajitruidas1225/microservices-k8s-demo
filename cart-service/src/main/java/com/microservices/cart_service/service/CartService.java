package com.microservices.cart_service.service;

import com.microservices.cart_service.dto.CartRequest;
import com.microservices.cart_service.entity.CartItem;
import com.microservices.cart_service.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public CartItem addToCart(CartRequest request) {
        CartItem item = CartItem.builder()
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .userId(request.getUserId())
                .build();
        return cartRepository.save(item);
    }

    public List<CartItem> getCart(Long userId) {
        return cartRepository.findByUserId(userId);
    }
}
