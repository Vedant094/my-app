package com.orderservice.controller;

import com.orderservice.dto.CreateOrderRequest;
import com.orderservice.entity.Order;
import com.orderservice.entity.OrderStatus;
import com.orderservice.service.OrderService;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest request){
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getOrder(id));
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable Long userId){
        return ResponseEntity.ok(orderService.getOrderByUser(userId));
    }

    @PutMapping("{id}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestParam OrderStatus status){
        return ResponseEntity.ok(orderService.updateStatus(id,status));
    }
}
