package com.restaurantservice.service;

import com.restaurantservice.dto.CreateMenuItemRequest;
import com.restaurantservice.entity.MenuItem;
import com.restaurantservice.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuItemRepository menuItemRepository;

    public MenuItem addMenuItem(CreateMenuItemRequest request){
        MenuItem item=new MenuItem();
        item.setRestaurantId(request.getRestaurantId());
        item.setName(request.getName());
        item.setPrice(request.getPrice());
        item.setAvailable(true);

        return menuItemRepository.save(item);
    }

    public List<MenuItem> getMenuByRestaurant(Long restaurantId){
        return menuItemRepository.findByRestaurantId(restaurantId);
    }
}
