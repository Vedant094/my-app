package com.orderservice.service;

import com.orderservice.client.NotificationClient;
import com.orderservice.client.OrderPlacedNotificationRequest;
import com.orderservice.client.RestaurantClient;
import com.orderservice.dto.CreateOrderRequest;
import com.orderservice.dto.MenuItemResponse;
import com.orderservice.dto.OrderItemRequest;
import com.orderservice.entity.Order;
import com.orderservice.entity.OrderItem;
import com.orderservice.entity.OrderStatus;
import com.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestaurantClient restaurantClient;

    // ✅ NEW: Notification REST client
    private final NotificationClient notificationClient;

    public Order createOrder(CreateOrderRequest request) {

        List<MenuItemResponse> menu = restaurantClient.getMenu(request.getRestaurantId());

        Map<Long, MenuItemResponse> menuMap = new HashMap<>();
        for (MenuItemResponse item : menu) {
            menuMap.put(item.getId(), item);
        }

        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setRestaurantId(request.getRestaurantId());
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> orderItems = new ArrayList<>();
        double total = 0;

        for (OrderItemRequest itemReq : request.getItems()) {

            MenuItemResponse menuItem = menuMap.get(itemReq.getMenuItemId());
            if (menuItem == null) {
                throw new RuntimeException("Invalid menu item: " + itemReq.getMenuItemId());
            }
            if (!menuItem.isAvailable()) {
                throw new RuntimeException("Item not available: " + menuItem.getName());
            }

            OrderItem oi = new OrderItem();
            oi.setMenuItemId(menuItem.getId());
            oi.setItemName(menuItem.getName());
            oi.setQuantity(itemReq.getQuantity());
            oi.setPriceSnapshot(menuItem.getPrice());
            oi.setOrder(order);

            orderItems.add(oi);

            total += (menuItem.getPrice() * itemReq.getQuantity());
        }

        order.setTotalAmount(total);
        order.setItems(orderItems);

        // ✅ Save first
        Order savedOrder = orderRepository.save(order);

        // ✅ NEW: Send REST notification (event-like)
        OrderPlacedNotificationRequest notifyReq = new OrderPlacedNotificationRequest();
        notifyReq.setOrderId(savedOrder.getId());
        notifyReq.setUserId(savedOrder.getUserId());
        notifyReq.setRestaurantId(savedOrder.getRestaurantId());
        notifyReq.setTotalAmount(savedOrder.getTotalAmount());
        notifyReq.setMessage("Order placed successfully!");

        notificationClient.sendOrderPlacedNotification(notifyReq);

        return savedOrder;
    }

    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> getOrderByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order updateStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);
        return orderRepository.save(order);
    }
}
