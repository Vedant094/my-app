package com.orderservice.client;

import lombok.Data;

@Data
public class OrderPlacedNotificationRequest {
    private Long orderId;
    private Long userId;
    private Long restaurantId;
    private double totalAmount;
    private String message;
}
