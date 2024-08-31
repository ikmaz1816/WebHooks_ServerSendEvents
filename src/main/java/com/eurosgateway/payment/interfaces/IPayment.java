package com.eurosgateway.payment.interfaces;

import com.eurosgateway.payment.dtos.PaymentResponseDTO;
import com.eurosgateway.payment.exceptions.PaymentException;
import com.eurosgateway.payment.model.Payment;

public interface IPayment {
    void payment(Payment payment);

    void notifyOnPaymentCompletion(PaymentResponseDTO paymentResponseDTO) throws PaymentException;
}
