package com.paymentservice.controller;

import com.paymentservice.dto.PayRequest;
import com.paymentservice.entity.Payment;
import com.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/pay")
    public ResponseEntity<Payment> pay(@RequestBody PayRequest request){
        return ResponseEntity.ok(paymentService.pay(request));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Payment> getPayment(@PathVariable Long orderId){
        return ResponseEntity.ok(paymentService.getPaymentByOrderId(orderId));
    }
}
