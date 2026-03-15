package com.microservices.payment_service.controller;

import com.microservices.payment_service.common.ApiResponse;
import com.microservices.payment_service.dto.PaymentRequest;
import com.microservices.payment_service.entity.Payment;
import com.microservices.payment_service.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping
    public ApiResponse<Payment> processPayment(@RequestBody PaymentRequest request){

        Payment payment = paymentService.processPayment(request);

        return ApiResponse.<Payment>builder()
                .success(true)
                .message("Payment successful")
                .data(payment)
                .build();
    }

}
