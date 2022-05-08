package com.example.estonianpizzaaBE.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Invalid payment status")
public class InvalidPaymentStatusException extends RuntimeException {
}
