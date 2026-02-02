//package com.notificationservice.consumer;
//
//import com.notificationservice.dto.OrderPlacedEvent;
//import com.notificationservice.dto.OrderPlacement;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class OrderEventsConsumer {
//    @KafkaListener(topics="order-event", groupId="notification-group")
//    public void consumerOrderPlaced(OrderPlacedEvent event){
//        System.out.println("Notification service recieved event");
//        System.out.println("OrderId: "+event.getOrderId());
//        System.out.println("UserId: "+event.getUserId());
//        System.out.println("RestaurantId: "+event.getRestaurantId());
//        System.out.println("Total: "+event.getTotalAmount());
//        System.out.println("Message: "+event.getMessage());
//        System.out.println("-----------------------------");
//
//
//
//    }
//}
