package com.eurosgateway.payment.service;

import com.eurosgateway.payment.dtos.PaymentResponseDTO;
import com.eurosgateway.payment.exceptions.PaymentException;
import com.eurosgateway.payment.interfaces.IPayment;
import com.eurosgateway.payment.model.Payment;
import com.eurosgateway.payment.model.Status;
import com.eurosgateway.payment.repository.PaymentRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Service
@Getter
public class PaymentService implements IPayment {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private SseEmitter sseEmitter;

    @Override
    public void payment(Payment payment) {
        payment.setStatus(Status.INPROGRESS);
        paymentRepository.save(payment);
    }

    @Override
    public void notifyOnPaymentCompletion(PaymentResponseDTO paymentResponseDTO) throws PaymentException{
       Payment euroPayment = paymentRepository.findById(paymentResponseDTO.id()).orElseThrow(()->new PaymentException("No Payment Found"));
        euroPayment.setStatus(paymentResponseDTO.status());
        paymentRepository.save(euroPayment);
        try {
            sseEmitter.send(SseEmitter.event()
                    .name("paymentComplete")
                    .data("Payment completed successfully for payment ID: " + euroPayment.getPaymentId()));
            sseEmitter.complete();
        } catch (IOException e) {
            sseEmitter.completeWithError(e);
        }
    }


}
