package com.paymentservice.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderClient {
    private final RestTemplate restTemplate=new RestTemplate();

    public void markOrderPaid(Long orderId){
        String url="http://localhost:8080/api/orders/"+orderId+"/status?status=PAID";
        restTemplate.put(url,null);
    }
}
