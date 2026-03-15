package com.microservices.order_service.dto;

import lombok.Data;

@Data
public class Payment {

    private Long id;
    private Long orderId;
    private Double amount;
    private String status;

}
