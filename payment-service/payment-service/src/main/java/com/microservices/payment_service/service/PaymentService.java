package com.microservices.payment_service.service;

import com.microservices.payment_service.dto.PaymentRequest;
import com.microservices.payment_service.entity.Payment;
import com.microservices.payment_service.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }

    public Payment processPayment(PaymentRequest request){

        Payment payment = Payment.builder()
                .orderId(request.getOrderId())
                .amount(request.getAmount())
                .status("SUCCESS")
                .build();

        return paymentRepository.save(payment);
    }

}
