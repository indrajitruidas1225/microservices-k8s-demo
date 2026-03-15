package com.microservices.order_service.client;

import com.microservices.order_service.common.ApiResponse;
import com.microservices.order_service.dto.Payment;
import com.microservices.order_service.dto.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service", url = "http://localhost:8084")
public interface PaymentClient {

    @PostMapping("/payments")
    ApiResponse<Payment> processPayment(@RequestBody PaymentRequest request);

}
