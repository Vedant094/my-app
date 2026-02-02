package com.orderservice.dto;

import lombok.Data;

@Data
public class MenuItemResponse {
    private Long id;
    private Long restaurantId;
    private String name;
    private double price;
    private boolean available;
}
