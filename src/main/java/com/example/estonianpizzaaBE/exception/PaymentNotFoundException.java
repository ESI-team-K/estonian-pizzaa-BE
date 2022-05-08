package com.example.estonianpizzaaBE.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.NOT_FOUND, reason = "Payment not found")
public class PaymentNotFoundException extends RuntimeException {
}
