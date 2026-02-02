package com.paymentservice.dto;

import lombok.Data;

@Data
public class PaymentSuccessNotificationRequest {
    private Long orderId;
    private double amount;
    private String paymentMode;
    private String message;
}
