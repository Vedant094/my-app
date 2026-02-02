package com.notificationservice.controller;

import com.notificationservice.dto.OrderPlacedNotificationRequest;
import com.notificationservice.dto.PaymentSuccessNotificationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @PostMapping("/order-placed")
    public ResponseEntity<String> orderPlaced(@RequestBody OrderPlacedNotificationRequest request) {

        System.out.println("\n✅ NOTIFICATION SENT (ORDER PLACED)");
        System.out.println("OrderId      : " + request.getOrderId());
        System.out.println("UserId       : " + request.getUserId()); // ✅ fixed
        System.out.println("RestaurantId : " + request.getRestaurantId());
        System.out.println("TotalAmount  : " + request.getTotalAmount());
        System.out.println("Message      : " + request.getMessage());
        System.out.println("---------------------------------------\n");

        return ResponseEntity.ok("Order placed notification logged ✅");
    }

    @PostMapping("/payment-success")
    public ResponseEntity<String> paymentSuccess(@RequestBody PaymentSuccessNotificationRequest request) {

        System.out.println("\n✅ NOTIFICATION SENT (PAYMENT SUCCESS)");
        System.out.println("OrderId      : " + request.getOrderId());
        System.out.println("Amount       : " + request.getAmount());
        System.out.println("PaymentMode  : " + request.getPaymentMode());
        System.out.println("Message      : " + request.getMessage());
        System.out.println("---------------------------------------\n");

        return ResponseEntity.ok("Payment success notification logged ✅");
    }
}
