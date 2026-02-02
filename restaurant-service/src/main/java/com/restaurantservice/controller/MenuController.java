package com.restaurantservice.controller;

import com.restaurantservice.dto.CreateMenuItemRequest;
import com.restaurantservice.entity.MenuItem;
import com.restaurantservice.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;
    @PostMapping
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody CreateMenuItemRequest request){
        return ResponseEntity.ok(menuService.addMenuItem(request));
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<MenuItem>> getMenu(@PathVariable Long restaurantId){
        return ResponseEntity.ok(menuService.getMenuByRestaurant(restaurantId));
    }
}
