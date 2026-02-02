package com.orderservice.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NotificationClient {
    private final RestTemplate restTemplate=new RestTemplate();

    public void sendOrderPlacedNotification(OrderPlacedNotificationRequest request){
        String url="http://localhost:8080/api/notifications/order-placed";

        try{
            ResponseEntity<String> response=
                    restTemplate.postForEntity(url,request,String.class);

            System.out.println("Notification service response: "+response.getBody());
        }

        catch (Exception e){
            System.out.println("Notification Service Failed: "+ e.getMessage());
        }
    }
}
