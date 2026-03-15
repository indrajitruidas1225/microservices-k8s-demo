package com.microservices.cart_service.dto;

import lombok.Data;

@Data
public class CartRequest {
    private Long userId;
    private Long productId;
    private Integer quantity;
}
