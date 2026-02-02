package com.paymentservice.dto;

import com.paymentservice.entity.PaymentMode;
import lombok.Data;

@Data
public class PayRequest {
    private Long orderId;
    private double amount;
    private PaymentMode paymentMode;
}
