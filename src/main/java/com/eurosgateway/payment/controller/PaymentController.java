package com.eurosgateway.payment.controller;

import com.eurosgateway.payment.dtos.PaymentResponseDTO;
import com.eurosgateway.payment.exceptions.PaymentException;
import com.eurosgateway.payment.model.Payment;
import com.eurosgateway.payment.service.PaymentService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/")
    public String healthCheck()
    {
        return "healthy";
    }

    @PostMapping("/webhook")
    public SseEmitter getPaymentStatus(@NonNull @RequestBody PaymentResponseDTO paymentResponseDTO) throws PaymentException {
        paymentService.notifyOnPaymentCompletion(paymentResponseDTO);
        return paymentService.getSseEmitter();
    }

    @PostMapping("/makepayment")
    public void makePayment(@NonNull @RequestBody Payment euroPayment)
    {
        paymentService.payment(euroPayment);
    }
}
