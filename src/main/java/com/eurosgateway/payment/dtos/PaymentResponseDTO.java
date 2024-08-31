package com.eurosgateway.payment.dtos;

import com.eurosgateway.payment.model.Status;

public record PaymentResponseDTO(long id, Status status) { }
