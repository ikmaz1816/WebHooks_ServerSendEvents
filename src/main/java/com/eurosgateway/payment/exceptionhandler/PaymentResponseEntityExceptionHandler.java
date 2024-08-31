package com.eurosgateway.payment.exceptionhandler;

import com.eurosgateway.payment.exceptions.PaymentException;
import com.eurosgateway.payment.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PaymentResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<ErrorMessage> paymentException(PaymentException exception)
    {
        ErrorMessage message = new ErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}
