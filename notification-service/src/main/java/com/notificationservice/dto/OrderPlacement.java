package com.notificationservice.dto;

import lombok.Data;

@Data
public class OrderPlacement {
    private Long orderId;
    private Long userId;
    private Long restaurantId;
    private double totalAmount;
    private String message;
}
