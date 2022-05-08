package com.example.estonianpizzaaBE.exception.payment;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.BAD_REQUEST, reason = "Invalid card")
public class InvalidCardException extends RuntimeException {
}
