package com.restaurantservice.controller;

import com.restaurantservice.dto.CreateRestaurantRequest;
import com.restaurantservice.entity.Restaurant;
import com.restaurantservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody CreateRestaurantRequest request){
        return ResponseEntity.ok(restaurantService.createRestaurant(request));
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants(){
        return ResponseEntity.ok(restaurantService.getAllRestaurant());
    }

    @GetMapping("/open")
    public ResponseEntity<List<Restaurant>> getOpenRestaurants(){
        return ResponseEntity.ok(restaurantService.getOpenRestaurants());
    }

    @PutMapping("/{id}/availability")
    public ResponseEntity<Restaurant> updateAvailability(@PathVariable Long id, @RequestParam boolean open){
        return ResponseEntity.ok(restaurantService.toggleAvailability(id,open));
    }
}
