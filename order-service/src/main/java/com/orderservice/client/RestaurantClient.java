package com.orderservice.client;

import com.orderservice.dto.MenuItemResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class RestaurantClient {
    private final RestTemplate restTemplate=new RestTemplate();

    public List<MenuItemResponse> getMenu(Long restaurantId){
        String url="http://localhost:8082/menu/"+restaurantId;

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null, new ParameterizedTypeReference<List<MenuItemResponse>>() {}
        ).getBody();
    }
}
