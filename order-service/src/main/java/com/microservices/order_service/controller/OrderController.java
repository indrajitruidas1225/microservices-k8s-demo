package com.microservices.order_service.controller;

import com.microservices.order_service.common.ApiResponse;
import com.microservices.order_service.dto.OrderRequest;
import com.microservices.order_service.entity.Order;
import com.microservices.order_service.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping
    public ApiResponse<Order> createOrder(@RequestBody OrderRequest request){
        Order order = orderService.createOrder(request);
        return ApiResponse.<Order>builder()
                .success(true)
                .message("Order created successfully")
                .data(order)
                .build();
    }
}
