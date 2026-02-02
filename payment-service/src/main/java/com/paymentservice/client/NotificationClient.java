package com.paymentservice.client;

import com.paymentservice.dto.PaymentSuccessNotificationRequest;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NotificationClient {
    private final RestTemplate restTemplate=new RestTemplate();


    public void sendPaymentSuccessNotification(PaymentSuccessNotificationRequest request){

        String url="http://localhost:8080/api/notifications/payment-success";

        try{
            ResponseEntity<String> response=
                    restTemplate.postForEntity(url,request,String.class);

            System.out.println("Notification Response: "+response.getBody());
        }
        catch(Exception e){
            System.out.println("Notification Failed: "+e.getMessage());
        }
    }
}
