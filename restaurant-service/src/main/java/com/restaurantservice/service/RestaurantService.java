package com.restaurantservice.service;

import com.restaurantservice.dto.CreateRestaurantRequest;
import com.restaurantservice.entity.Restaurant;
import com.restaurantservice.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    
    public Restaurant createRestaurant(CreateRestaurantRequest request){
        Restaurant restaurant=new Restaurant();
        restaurant.setName(request.getName());
        restaurant.setAddress(request.getAddress());
        restaurant.setOwnerId(request.getOwnerId());
        restaurant.setOpen(true);
        restaurant.setCreatedAt(LocalDateTime.now());
        
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAllRestaurant(){
        return restaurantRepository.findAll();
    }
    
    public List<Restaurant> getOpenRestaurants(){
        return restaurantRepository.findByIsOpenTrue();
    }
    
    public Restaurant toggleAvailability(Long restaurantId,boolean open){
        Restaurant restaurant=restaurantRepository.findById(restaurantId).orElseThrow(()->new RuntimeException("Restaurant Not Found"));
        restaurant.setOpen(open);
        return restaurantRepository.save(restaurant);
    }
}
