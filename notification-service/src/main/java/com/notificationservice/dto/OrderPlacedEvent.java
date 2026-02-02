package com.notificationservice.dto;

import lombok.Data;

@Data
public class OrderPlacedEvent {
    private Long orderId;
    private Long userId;
    private Long restaurantId;
    private double totalAmount;
    private String message;
}
