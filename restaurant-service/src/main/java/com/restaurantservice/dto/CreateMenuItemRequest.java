package com.restaurantservice.dto;

import lombok.Data;

@Data
public class CreateMenuItemRequest {
    private Long restaurantId;
    private String name;
    private double price;
}
