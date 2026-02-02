package com.paymentservice.service;

import com.paymentservice.client.OrderClient;
import com.paymentservice.dto.PayRequest;
import com.paymentservice.entity.Payment;
import com.paymentservice.entity.PaymentStatus;
import com.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderClient orderClient;

    public Payment pay(PayRequest request){
        Payment payment= new Payment();
        payment.setOrderId(request.getOrderId());
        payment.setAmount(request.getAmount());
        payment.setPaymentMode(request.getPaymentMode());
        payment.setCreatedAt(LocalDateTime.now());


        if(request.getAmount()>0){
            payment.setPaymentStatus(PaymentStatus.SUCCESS);

            orderClient.markOrderPaid(request.getOrderId());
        }
        else{
            payment.setPaymentStatus(PaymentStatus.FAILED);
        }
        return paymentRepository.save(payment);
    }

    public Payment getPaymentByOrderId(Long orderId){
        return paymentRepository.findByOrderId(orderId).orElseThrow(()->new RuntimeException("Payment not found"));
    }
}
