package com.microservices.order_service.client;

import com.microservices.order_service.common.ApiResponse;
import com.microservices.order_service.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8081")
public interface UserClient {

    @GetMapping("/users/{userId}")
    ApiResponse<User> getUser(@PathVariable Long userId);

}
