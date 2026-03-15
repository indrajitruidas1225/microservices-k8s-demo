package com.microservices.order_service.client;

import com.microservices.order_service.dto.CartItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "cart-service", url = "http://localhost:8082")
public interface CartClient {

    @GetMapping("/cart/{userId}")
    List<CartItem> getCart(@PathVariable Long userId);

}