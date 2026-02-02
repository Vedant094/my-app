package com.restaurantservice.dto;

import lombok.Data;

@Data
public class CreateRestaurantRequest {
    private String name;
    private String address;
    private Long ownerId;
}
