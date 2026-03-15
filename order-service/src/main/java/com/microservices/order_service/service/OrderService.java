package com.microservices.order_service.service;

import com.microservices.order_service.client.CartClient;
import com.microservices.order_service.client.PaymentClient;
import com.microservices.order_service.client.UserClient;
import com.microservices.order_service.common.ApiResponse;
import com.microservices.order_service.dto.*;
import com.microservices.order_service.entity.Order;
import com.microservices.order_service.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserClient userClient;
    private final CartClient cartClient;
    private final PaymentClient paymentClient;
    public OrderService(OrderRepository orderRepository, UserClient userClient, CartClient cartClient,  PaymentClient paymentClient) {
        this.orderRepository = orderRepository;
        this.userClient = userClient;
        this.cartClient = cartClient;
        this.paymentClient = paymentClient;
    }

    @Transactional
    public Order createOrder(OrderRequest request){
        ApiResponse<User> userResponse = userClient.getUser(request.getUserId());
        if(!userResponse.isSuccess()){
            throw new RuntimeException("User not found");
        }
        List<CartItem> cartItems = cartClient.getCart(request.getUserId());
        if(cartItems.isEmpty()){
            throw new RuntimeException("Cart is empty");
        }
        double total = cartItems.size() * 100;

        // Step 1 → create order with PENDING
        Order order = Order.builder()
                .userId(request.getUserId())
                .totalAmount(total)
                .status("PENDING")
                .build();

        order = orderRepository.save(order);

        // Step 2 → call payment
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setOrderId(order.getId());
        paymentRequest.setAmount(total);
        try{
            ApiResponse<Payment> paymentResponse =
                    paymentClient.processPayment(paymentRequest);
            if(paymentResponse.isSuccess()){
                order.setStatus("CONFIRMED");
            }else{
                order.setStatus("CANCELLED");
            }
        }catch(Exception e){
            order.setStatus("CANCELLED");
        }
        return orderRepository.save(order);
    }
}
