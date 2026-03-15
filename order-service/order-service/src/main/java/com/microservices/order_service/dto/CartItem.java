package com.microservices.order_service.dto;

import lombok.Data;

@Data
public class CartItem {

    private Long id;
    private Long userId;
    private Long productId;
    private Integer quantity;

}